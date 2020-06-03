<?php

namespace eventBundle\Controller;

use eventBundle\Entity\enfant;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Symfony\Component\HttpFoundation\Request;

class enfantController extends Controller
{

    public function addAction(Request $request)

    {
        $enfant = new enfant();
        $form = $this->createForm(enfantType::class, $enfant);
        $form->handleRequest($request);
        if ($form->isSubmitted() and $form->isValid()) {
            $em = $this->getDoctrine()->getManager();
            $em->persist($enfant);
            $em->flush();
            $this->addFlash('success','ajoutée avec succée!');
           // return $this->redirectToRoute('read_enfant');
        }
        return $this->render('@event/enfant/add.html.twig', array(
            'form' => $form->createView()

        ));
    }

    public function readEnfantAction()
    {
        $em = $this->getDoctrine()->getRepository(enfant::class);
        $list = $em->findAll();
        return $this->render('@event/enfant/read.html.twig', array('enfants' => $list));
    }
}
