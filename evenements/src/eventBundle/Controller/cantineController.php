<?php

namespace eventBundle\Controller;

use eventBundle\Entity\cantine;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Symfony\Component\HttpFoundation\Request;

/**
 * Cantine controller.
 *
 */
class cantineController extends Controller
{
    /**
     * Lists all cantine entities.
     *
     */
    public function indexAction()
    {
        $em = $this->getDoctrine()->getManager();

        $cantines = $em->getRepository('eventBundle:cantine')->findAll();



        return $this->render('cantine/index.html.twig', array(
            'cantines' => $cantines,
        ));
    }

    /**
     * Creates a new cantine entity.
     *
     */
    public function newAction(Request $request)
    {
        $cantine = new Cantine();
        $form = $this->createForm('eventBundle\Form\cantineType', $cantine);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $em = $this->getDoctrine()->getManager();
            $em->persist($cantine);
            $em->flush();

            return $this->redirectToRoute('@event/cantine_show', array('id' => $cantine->getId()));
        }

        return $this->render('@event/cantine/new.html.twig', array(
            'cantine' => $cantine,
            'form' => $form->createView(),
        ));
    }

    /**
     * Finds and displays a cantine entity.
     *
     */
    public function showAction(cantine $cantine)
    {
        $deleteForm = $this->createDeleteForm($cantine);

        return $this->render('@event/cantine/show.html.twig', array(
            'cantine' => $cantine,
            'delete_form' => $deleteForm->createView(),
        ));
    }

    /**
     * Displays a form to edit an existing cantine entity.
     *
     */
    public function editAction(Request $request, cantine $cantine)
    {
        $deleteForm = $this->createDeleteForm($cantine);
        $editForm = $this->createForm('eventBundle\Form\cantineType', $cantine);
        $editForm->handleRequest($request);

        if ($editForm->isSubmitted() && $editForm->isValid()) {
            $this->getDoctrine()->getManager()->flush();

            return $this->redirectToRoute('@event/cantine_edit', array('id' => $cantine->getId()));
        }

        return $this->render('@event/cantine/edit.html.twig', array(
            'cantine' => $cantine,
            'edit_form' => $editForm->createView(),
            'delete_form' => $deleteForm->createView(),
        ));
    }

    /**
     * Deletes a cantine entity.
     *
     */
    public function deleteAction(Request $request, cantine $cantine)
    {
        $form = $this->createDeleteForm($cantine);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $em = $this->getDoctrine()->getManager();
            $em->remove($cantine);
            $em->flush();
        }

        return $this->redirectToRoute('@event/cantine_index');
    }

    /**
     * Creates a form to delete a cantine entity.
     *
     * @param cantine $cantine The cantine entity
     *
     * @return \Symfony\Component\Form\Form The form
     */
    private function createDeleteForm(cantine $cantine)
    {
        return $this->createFormBuilder()
            ->setAction($this->generateUrl('@event/cantine_delete', array('id' => $cantine->getId())))
            ->setMethod('DELETE')
            ->getForm()
            ;
    }
}
