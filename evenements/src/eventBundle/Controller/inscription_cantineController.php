<?php

namespace eventBundle\Controller;

use eventBundle\Entity\inscription_cantine;
use eventBundle\Entity\cantine;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Symfony\Component\HttpFoundation\Request;
use Doctrine\ORM\Query\AST\Functions\CurrentDateFunction;
use eventBundle\Form\inscription_evenementType;
use Symfony\Component\HttpFoundation\Response;
use eventBundle\Entity\enfant;
use Knp\Bundle\SnappyBundle\Snappy\Response\PdfResponse;


use Sensio\Bundle\FrameworkExtraBundle\Configuration\Route;
use Doctrine\Common\Persistence\PersistentObject;

/**
 * Inscription_cantine controller.
 *
 */
class inscription_cantineController extends Controller
{
    /**
     * Lists all inscription_cantine entities.
     *
     */
    public function indexAction(Request $request)
    {

        $em = $this->getDoctrine()->getManager();

        $inscription_cantines = $em->getRepository('eventBundle:inscription_cantine')->findAllOrderedBydate();

        $paginator = $this->get('knp_paginator');

        $result = $paginator->paginate(
            $inscription_cantines,
            $request->query->getInt('page', 1),
            $request->query->getInt('limit', 3)

        );

        return $this->render('@event/inscription_cantine/index.html.twig', array(
            'inscription_cantines' => $result,
        ));
    }

    /**
     * Creates a new inscription_cantine entity.
     *
     */
    public function newAction(Request $request)
    {





        $em = $this->getDoctrine()->getManager();
        $user=$this->getUser();
        $idu=$user->getId();
        $myenfant = $em->getRepository('eventBundle:enfant')->findAllEn($idu);



        $cantine = $em->getRepository(cantine::class)->find(1);
        $nb = $cantine->getCapacite();

        $inscription_cantine = new Inscription_cantine();
        $form = $this->createForm('eventBundle\Form\inscription_cantineType', $inscription_cantine);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid() && $nb>0) {
            $inscription_cantine->setIdCant($cantine);
            $em = $this->getDoctrine()->getManager();
            $em->persist($inscription_cantine);
            $nb = $cantine->setCapacite($nb - 1);
            $em->flush();

            return $this->redirectToRoute('inscription_cantine_show', array('id' => $inscription_cantine->getId()));
        }

        return $this->render('@event/inscription_cantine/new.html.twig', array(
            'inscription_cantine' => $inscription_cantine,
            'form' => $form->createView(),
            'myenfants'=>$myenfant,
        ));
    }

    /**
     * Finds and displays a inscription_cantine entity.
     *
     */
    public function showAction(inscription_cantine $inscription_cantine)
    {
        $deleteForm = $this->createDeleteForm($inscription_cantine);

        return $this->render('@event/inscription_cantine/show.html.twig', array(
            'inscription_cantine' => $inscription_cantine,
            'delete_form' => $deleteForm->createView(),
        ));
    }




        public function pdfAction($id)
    {

        $em = $this->getDoctrine()->getRepository(inscription_cantine::class);
        $ins = $em->find($id);
        $html = $this->renderView('eventBundle:inscription_cantine:pdf_facture.html.twig', array(
            'inscription_cantine'  => $ins
        ));

        return new PdfResponse(
            $this->get('knp_snappy.pdf')->getOutputFromHtml($html),
            'Inscription_cantine.pdf'
        );
    }


    /**
     * Displays a form to edit an existing inscription_cantine entity.
     *
     */
    public function editAction(Request $request, inscription_cantine $inscription_cantine)
    {
        $deleteForm = $this->createDeleteForm($inscription_cantine);
        $editForm = $this->createForm('eventBundle\Form\inscription_cantineType', $inscription_cantine);
        $editForm->handleRequest($request);

        if ($editForm->isSubmitted() && $editForm->isValid()) {
            $this->getDoctrine()->getManager()->flush();

            return $this->redirectToRoute('inscription_cantine_edit', array('id' => $inscription_cantine->getId()));
        }

        return $this->render('@event/inscription_cantine/edit.html.twig', array(
            'inscription_cantine' => $inscription_cantine,
            'edit_form' => $editForm->createView(),
            'delete_form' => $deleteForm->createView(),
        ));
    }

    /**
     * Deletes a inscription_cantine entity.
     *
     */
    public function deleteAction(Request $request, inscription_cantine $inscription_cantine)
    {
        $form = $this->createDeleteForm($inscription_cantine);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $em = $this->getDoctrine()->getManager();
            $em->remove($inscription_cantine);
            $em->flush();
        }

        return $this->redirectToRoute('inscription_cantine_index');
    }


    public function delete2Action($id){
        $em=$this->getDoctrine()->getManager();
        $menu=$em->getRepository(inscription_cantine::class)->find($id);
        $em->remove($menu);
        $em->flush();
        return $this->redirectToRoute('inscription_cantine_index');
    }


    /**
     * Creates a form to delete a inscription_cantine entity.
     *
     * @param inscription_cantine $inscription_cantine The inscription_cantine entity
     *
     * @return \Symfony\Component\Form\Form The form
     */
    private function createDeleteForm(inscription_cantine $inscription_cantine)
    {
        return $this->createFormBuilder()
            ->setAction($this->generateUrl('inscription_cantine_delete', array('id' => $inscription_cantine->getId())))
            ->setMethod('DELETE')
            ->getForm()
            ;
    }



    public function searchcAction(Request $request)
    {
        $em = $this->getDoctrine()->getManager();
        $libelle = $request->get('q');
        $rec = $em->getRepository('eventBundle:inscription_cantine')->Searchinscri($libelle);

        if (!$rec) {
            $result['inscription_cantine']['error'] = "Inscription does not exist :( ";
        } else {
            $result['inscription_cantine'] = $this->getRealEntities($rec);
        }

        return new Response(json_encode($result));
    }
    public function getRealEntities($inscription_cantine){
        foreach ($inscription_cantine as $inscription_cantines){
            $realEntities[$inscription_cantines->getId()] = [$inscription_cantines-> getIdEnf()->getNom() ];

        }
        return $realEntities;
    }







}
