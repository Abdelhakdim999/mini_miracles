<?php

namespace eventBundle\Controller;

use eventBundle\Entity\pack;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Sensio\Bundle\FrameworkExtraBundle\Configuration\Method;
use Sensio\Bundle\FrameworkExtraBundle\Configuration\Route;
use Symfony\Component\HttpFoundation\Response;
//use Symfony\Component\HttpFoundation\Request;
use Sensio\Bundle\FrameworkExtraBundle\Configuration\ParamConverter;
use Symfony\Component\HttpFoundation\Request;



/**
 * Pack controller.
 *
 * @Route("pack")
 */
class packController extends Controller
{
    /**
     * Lists all pack entities.
     *
     * @Route("/", name="pack_index")
     * @Method("GET")
     */
    public function indexAction(Request $request)
    {
        $em = $this->getDoctrine()->getManager();

        $packs = $em->getRepository('eventBundle:pack')->findAllOrderedById();
        $paginator = $this->get('knp_paginator');

        $result = $paginator->paginate(
            $packs,
            $request->query->getInt('page', 1),
            $request->query->getInt('limit', 3)
        );



        return $this->render('@event/pack/index.html.twig', array(
            'packs' => $result,

        ));
    }


    /**
     * Creates a new pack entity.
     *
     * @Route("/new", name="pack_new")
     * @Method({"GET", "POST"})
     */
    public function newAction(Request $request)
    {
        $pack = new Pack();
        $form = $this->createForm('eventBundle\Form\packType', $pack);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $em = $this->getDoctrine()->getManager();

            $file = $pack->getImage();
            $fileName = md5(uniqid()) . '.' . $file->guessExtension();
            $file->move($this->getParameter('upload_directory'), $fileName);
            $pack->setImage($fileName);

            $em->persist($pack);
            $em->flush();

            $this->addFlash('success', 'ajoutée avec succée!');
            return $this->redirectToRoute('pack_index', array('id' => $pack->getId()));
        }

        return $this->render('@event/pack/new.html.twig', array(
            'pack' => $pack,
            'form' => $form->createView(),
        ));
    }

    /**
     * Finds and displays a pack entity.
     *
     * @Route("/{id}", name="pack_show")
     * @Method("GET")
     */
    public function showAction(pack $pack)
    {

        return $this->render('@event/pack/show.html.twig', array(
            'pack' => $pack,
        ));
    }

    /**
     * Displays a form to edit an existing pack entity.
     *
     * @Route("/{id}/edit", name="pack_edit")
     * @Method({"GET", "POST"})
     */
    public function editAction(Request $request, pack $pack)
    {

        $editForm = $this->createForm('eventBundle\Form\packType', $pack);
        $editForm->handleRequest($request);

        if ($editForm->isSubmitted() && $editForm->isValid()) {
            $this->getDoctrine()->getManager()->flush();

            return $this->redirectToRoute('pack_index', array('id' => $pack->getId()));
        }

        return $this->render('@event/pack/edit.html.twig', array(
            'pack' => $pack,
            'edit_form' => $editForm->createView(),
        ));
    }

    /**
     * Deletes a pack entity.
     *
     * @Route("/{id}", name="pack_delete")
     * @Method("DELETE")
     */
    public function deleteAction($id)

    {

        $em = $this->getDoctrine()->getManager();
        $pack=$em->getRepository(pack::class)->find($id);
        $pack->getId();
        $em->remove($pack);
        $em->flush();


        return $this->redirectToRoute('pack_index');
    }



    //la partie front
    /**
     * Lists all pack entities.
     *
     * @Route("/frontt", name="pack_index_front")
     * @Method({"GET", "POST"})
     */

    public function fronttAction(Request $request)
    {

        $em = $this->getDoctrine()->getManager();

        $packs = $em->getRepository('eventBundle:pack')->findAllOrderedById();
        $paginator = $this->get('knp_paginator');

        $result = $paginator->paginate(
            $packs,
            $request->query->getInt('page', 1),
            $request->query->getInt('limit', 3)

        );



        return $this->render('@event/pack/indexFront.html.twig', array(
            'packs' => $result,

        ));
    }

    public function showFrontAction(pack $pack)
    {

        return $this->render('@event/pack/showPackF.html.twig', array(
            'pack' => $pack,
        ));
    }






}
