<?php

namespace eventBundle\Controller;

use eventBundle\Entity\menu;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;

/**
 * Menu controller.
 *
 */
class menuController extends Controller
{
    /**
     * Lists all menu entities.
     *
     */
    public function indexAction(Request $request)
    {
        $em = $this->getDoctrine()->getManager();

        $menus = $em->getRepository('eventBundle:menu')->findAllOrderedBydate();

        $paginator = $this->get('knp_paginator');

        $result = $paginator->paginate(
            $menus,
            $request->query->getInt('page', 1),
            $request->query->getInt('limit', 3)

        );

        return $this->render('@event/menu/index.html.twig', array(
            'menus' => $result,
        ));
    }

    /**
     * Creates a new menu entity.
     *
     */
    public function newAction(Request $request)
    {
        $menu = new Menu();
        $form = $this->createForm('eventBundle\Form\menuType', $menu);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $em = $this->getDoctrine()->getManager();
            $em->persist($menu);
            $em->flush();

            return $this->redirectToRoute('menu_show', array('id' => $menu->getId()));
        }

        return $this->render('@event/menu/new.html.twig', array(
            'menu' => $menu,
            'form' => $form->createView(),
        ));
    }

    /**
     * Finds and displays a menu entity.
     *
     */
    public function showAction(menu $menu)
    {
        $deleteForm = $this->createDeleteForm($menu);

        return $this->render('@event/menu/show.html.twig', array(
            'menu' => $menu,
            'delete_form' => $deleteForm->createView(),
        ));
    }

    /**
     * Displays a form to edit an existing menu entity.
     *
     */
    public function editAction(Request $request, menu $menu)
    {
        $deleteForm = $this->createDeleteForm($menu);
        $editForm = $this->createForm('eventBundle\Form\menuType', $menu);
        $editForm->handleRequest($request);

        if ($editForm->isSubmitted() && $editForm->isValid()) {
            $this->getDoctrine()->getManager()->flush();

            return $this->redirectToRoute('menu_edit', array('id' => $menu->getId()));
        }

        return $this->render('@event/menu/edit.html.twig', array(
            'menu' => $menu,
            'edit_form' => $editForm->createView(),
            'delete_form' => $deleteForm->createView(),
        ));
    }

    /**
     * Deletes a menu entity.
     *
     */
    public function deleteAction(Request $request, menu $menu)
    {
        $form = $this->createDeleteForm($menu);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $em = $this->getDoctrine()->getManager();
            $em->remove($menu);
            $em->flush();
        }

        return $this->redirectToRoute('menu_index');
    }

    public function delete2Action($id)
    {
        $em = $this->getDoctrine()->getManager();
        $menu = $em->getRepository(menu::class)->find($id);
        $em->remove($menu);
        $em->flush();
        return $this->redirectToRoute('menu_index');
    }



    //1.affichage du form search




    /**
     * Creates a form to delete a menu entity.
     *
     * @param menu $menu The menu entity
     *
     * @return \Symfony\Component\Form\Form The form
     */
    private function createDeleteForm(menu $menu)
    {
        return $this->createFormBuilder()
            ->setAction($this->generateUrl('menu_delete', array('id' => $menu->getId())))
            ->setMethod('DELETE')
            ->getForm()
            ;
    }





    public function searchcAction(Request $request)
    {
        $em = $this->getDoctrine()->getManager();
        $libelle = $request->get('q');
        $rec = $em->getRepository('eventBundle:menu')->SearchMenu($libelle);

        if (!$rec) {
            $result['menu']['error'] = "Menu does not exist :( ";
        } else {
            $result['menu'] = $this->getRealEntities($rec);
        }

        return new Response(json_encode($result));
    }
    public function getRealEntities($menus){
        foreach ($menus as $menus){
            $realEntities[$menus->getId()] = [$menus->getPlat()];

        }
        return $realEntities;
    }










}
