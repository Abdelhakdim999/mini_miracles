<?php

namespace eventBundle\Controller;

use eventBundle\Entity\commentaire;
use eventBundle\Entity\sujet;
use eventBundle\Form\commentaireType;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Symfony\Component\HttpFoundation\Request;

/**
 * Commentaire controller.
 *
 */
class commentaireController extends Controller
{
    /**
     * Lists all commentaire entities.
     *
     */
    public function indexAction(Request $request)
    {
        $em = $this->getDoctrine()->getManager();

        $list = $em->getRepository('eventBundle:commentaire')->findAll();

        $paginator = $this->get('knp_paginator');

        $result = $paginator->paginate(
            $list,
            $request->query->getInt('page', 1),
            $request->query->getInt('limit', 3)

        );

        return $this->render('@event/commentaire/index.html.twig', array(
            'commentaires' => $result,
        ));
    }


    /**
     * Creates a new commentaire entity.
     *
     */
    public function newAction(Request $request)
    {
        $commentaire = new Commentaire();
        $form = $this->createForm('eventBundle\Form\commentaireType', $commentaire);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $em = $this->getDoctrine()->getManager();
            $em->persist($commentaire);
            $em->flush();

            return $this->redirectToRoute('commentaire_show', array('id' => $commentaire->getId()));
        }

        return $this->render('@event/commentaire/new.html.twig', array(
            'commentaire' => $commentaire,
            'form' => $form->createView(),
        ));
    }

    /**
     * Finds and displays a commentaire entity.
     *
     */
    public function showAction(commentaire $commentaire)
    {
        $deleteForm = $this->createDeleteForm($commentaire);

        return $this->render('@event/commentaire/show.html.twig', array(
            'commentaire' => $commentaire,
            'delete_form' => $deleteForm->createView(),
        ));
    }

    public function show2Action(commentaire $commentaire)
    {
        $deleteForm = $this->createDeleteForm($commentaire);

        return $this->render('@event/commentaire/show2.html.twig', array(
            'commentaire' => $commentaire,
            'delete_form' => $deleteForm->createView(),
        ));
    }

    /**
     * Displays a form to edit an existing commentaire entity.
     *
     */
    public function editAction(Request $request, commentaire $commentaire)
    {
        $deleteForm = $this->createDeleteForm($commentaire);
        $editForm = $this->createForm('eventBundle\Form\commentaireType', $commentaire);
        $editForm->handleRequest($request);

        if ($editForm->isSubmitted() && $editForm->isValid()) {
            $this->getDoctrine()->getManager()->flush();

            return $this->redirectToRoute('commentaire_edit', array('id' => $commentaire->getId()));
        }

        return $this->render('@event/commentaire/edit.html.twig', array(
            'commentaire' => $commentaire,
            'edit_form' => $editForm->createView(),
            'delete_form' => $deleteForm->createView(),
        ));
    }

    /**
     * Deletes a commentaire entity.
     *
     */
    public function deleteAction(Request $request, commentaire $commentaire)
    {
        $form = $this->createDeleteForm($commentaire);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $em = $this->getDoctrine()->getManager();
            $em->remove($commentaire);
            $em->flush();
        }

        return $this->redirectToRoute('commentaire_index');
    }


    public function readcomsujetAction($id)
    {
        $em = $this->getDoctrine()->getManager();
        $sujet = $em->getRepository(sujet::class)->find($id);

        $commentaires = $this->getDoctrine()->getManager()
            ->getRepository(commentaire::class)->find_com_sujet($id);
        return $this->render('@event/commentaire/index2.html.twig', array('commentaires' => $commentaires));
    }

    /**
     * Creates a form to delete a commentaire entity.
     *
     * @param commentaire $commentaire The commentaire entity
     *
     * @return \Symfony\Component\Form\Form The form
     */
    private function createDeleteForm(commentaire $commentaire)
    {
        return $this->createFormBuilder()
            ->setAction($this->generateUrl('commentaire_delete', array('id' => $commentaire->getId())))
            ->setMethod('DELETE')
            ->getForm();
    }

    private function AjoutCommAction($id, Request $request)
    {

        $em = $this->getDoctrine()->getManager();
        $sujet = $em->getRepository(sujet::class)->find($id);

        $commentaire = new commentaire();
        $form = $this->createForm(commentaireType::class, $commentaire);
        $form->handleRequest($request);


        if ($form->isSubmitted() and $form->isValid()) {

            $commentaire->setIdSujet($id);
            $commentaire->setIdUser(1);
            $commentaire->setDate(new \DateTime('now'));

            $em = $this->getDoctrine()->getManager();
            $em->persist($commentaire);
            $em->flush();

            return $this->redirectToRoute('sujet_readcomsujet');

        }
        return $this->render('@event/commentaire/index2.html.twig', array(
            'form' => $form->createView(),

        ));
    }


    public function sujcommAction($id, Request $request)
    {
        $em = $this->getDoctrine()->getManager();
        $sujet = $em->getRepository(sujet::class)->find($id);

        $commentaire = new commentaire();
        $form = $this->createForm(commentaireType::class, $commentaire);
        $form->handleRequest($request);

        if ($form->isSubmitted() and $form->isValid()) {

            $commentaire->setIdSujet($sujet);

            $commentaire->setDate(new \DateTime('now'));


            $em = $this->getDoctrine()->getManager();
            $em->persist($commentaire);

            $em->flush();

            $this->addFlash('success', 'ajoutée avec succée!');


           // return $this->redirectToRoute('sujet_readcomsujet2');
        }

        return $this->render('@event/commentaire/new.html.twig', array(
            'form' => $form->createView(),

        ));
    }

}
