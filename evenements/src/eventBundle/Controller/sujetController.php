<?php

namespace eventBundle\Controller;

use eventBundle\Entity\commentaire;
use eventBundle\Entity\sujet;
use eventBundle\Form\commentaireType;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;

/**
 * Sujet controller.
 *
 */
class sujetController extends Controller
{
    /**
     * Lists all sujet entities.
     *
     */
    public function indexAction(Request $request)
    {
        $em = $this->getDoctrine()->getManager();

        $list = $em->getRepository('eventBundle:sujet')->findAll();

        $paginator = $this->get('knp_paginator');

        $result = $paginator->paginate(
            $list,
            $request->query->getInt('page', 1),
            $request->query->getInt('limit', 3)

        );

        return $this->render('@event/sujet/index.html.twig', array(
            'sujets' => $result,
        ));
    }
    public function index2Action(Request $request)
    {
        $em = $this->getDoctrine()->getManager();

        $list = $em->getRepository('eventBundle:sujet')->findAll();

        $paginator = $this->get('knp_paginator');

        $result = $paginator->paginate(
            $list,
            $request->query->getInt('page', 1),
            $request->query->getInt('limit', 3)

        );

        return $this->render('@event/sujet/index2.html.twig', array(
            'sujets' => $result,
        ));
    }

    /**
     * Creates a new sujet entity.
     *
     */
    public function newAction(Request $request)
    {
        $sujet = new Sujet();
        $form = $this->createForm('eventBundle\Form\sujetType', $sujet);
        $form->handleRequest($request);

        $contenu=$sujet->getContenu();

        if ($form->isSubmitted() && $form->isValid()) {
            $em = $this->getDoctrine()->getManager();
            $sujet->setDate(new \DateTime('now'));
            $em->persist($sujet);
            $em->flush();

            return $this->redirectToRoute('sujet_index', array('id' => $sujet->getId()));
        }

        return $this->render('@event/sujet/new.html.twig', array(
            'sujet' => $sujet,
            'contenu' => $contenu,
            'form' => $form->createView(),
        ));
    }

    /**
     * Finds and displays a sujet entity.
     *
     */
    public function showAction(sujet $sujet)
    {
        $deleteForm = $this->createDeleteForm($sujet);

        return $this->render('@event/sujet/show.html.twig', array(
            'sujet' => $sujet,
            'delete_form' => $deleteForm->createView(),
        ));
    }

    public function show2Action(sujet $sujet)
    {
        $deleteForm = $this->createDeleteForm($sujet);

        return $this->render('@event/sujet/show2.html.twig', array(
            'sujet' => $sujet,
            'delete_form' => $deleteForm->createView(),
        ));
    }

    public function readcomsujetAction($id)
    {
        $em = $this->getDoctrine()->getManager();
        $sujet = $em->getRepository(sujet::class)->find($id);

        $commentaires=$this->getDoctrine()->getManager()
            ->getRepository(commentaire::class)->find_com_sujet($id);
        return $this->render('@event/commentaire/index.html.twig', array('commentaires' => $commentaires));
    }

    public function readcomsujet2Action($id, Request $request)
    {
        $em = $this->getDoctrine()->getManager();
        $sujet = $em->getRepository(sujet::class)->find($id);

        $commentaires=$this->getDoctrine()->getManager()
            ->getRepository(commentaire::class)->find_com_sujet($id);



        $paginator = $this->get('knp_paginator');

        $result = $paginator->paginate(
            $commentaires,
            $request->query->getInt('page', 1),
            $request->query->getInt('limit', 3)

        );
        return $this->render('@event/commentaire/indexfront.html.twig', array('commentaires' => $commentaires));
    }







    /**
     * Displays a form to edit an existing sujet entity.
     *
     */
    public function editAction(Request $request, sujet $sujet)
    {
        $deleteForm = $this->createDeleteForm($sujet);
        $editForm = $this->createForm('eventBundle\Form\sujetType', $sujet);
        $editForm->handleRequest($request);

        if ($editForm->isSubmitted() && $editForm->isValid()) {
            $this->getDoctrine()->getManager()->flush();

            return $this->redirectToRoute('sujet_edit', array('id' => $sujet->getId()));
        }

        return $this->render('@event/sujet/edit.html.twig', array(
            'sujet' => $sujet,
            'edit_form' => $editForm->createView(),
            'delete_form' => $deleteForm->createView(),
        ));
    }

    /**
     * Deletes a sujet entity.
     *
     */
    public function deleteAction(Request $request, sujet $sujet)
    {
        $form = $this->createDeleteForm($sujet);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $em = $this->getDoctrine()->getManager();
            $em->remove($sujet);
            $em->flush();
        }

        return $this->redirectToRoute('sujet_index');
    }


    public function delete2Action($id)
    {
        $em = $this->getDoctrine()->getManager();
        $sujet = $em->getRepository(sujet::class)->find($id);
        $em->remove($sujet);
        $em->flush();
        return $this->redirectToRoute('sujet_index');
    }

    /**
     * Creates a form to delete a sujet entity.
     *
     * @param sujet $sujet The sujet entity
     *
     * @return \Symfony\Component\Form\Form The form
     */
    private function createDeleteForm(sujet $sujet)
    {
        return $this->createFormBuilder()
            ->setAction($this->generateUrl('sujet_delete', array('id' => $sujet->getId())))
            ->setMethod('DELETE')
            ->getForm()
            ;
    }


    public function searchcAction(Request $request)
    {
        $em = $this->getDoctrine()->getManager();
        $libelle = $request->get('q');
        $rec = $em->getRepository('eventBundle:sujet')->Searchinscri($libelle);

        if (!$rec) {
            $result['sujet']['error'] = "sujet does not exist :( ";
        } else {
            $result['sujet'] = $this->getRealEntities($rec);
        }

        return new Response(json_encode($result));
    }
    public function getRealEntities($sujet){
        foreach ($sujet as $sujets){
            $realEntities[$sujets->getId()] = [$sujets-> getContenu() ];

        }
        return $realEntities;
    }

    public function readOneAction($id)
    {
        $em = $this->getDoctrine()->getRepository(sujet::class);
        $sujet = $em->find($id);

        return $this->render('@event/sujet/show.html.twig', array('sujet' => $sujet));
    }
}
