<?php

namespace eventBundle\Controller;

use eventBundle\Entity\inscription_evenement;
use eventBundle\Form\inscription_evenementType;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Symfony\Component\HttpFoundation\Request;

class inscription_evenementController extends Controller
{

    public function addAction(Request $request)

    {
        $inscription_evenement = new inscription_evenement();
        $form = $this->createForm(inscription_evenementType::class, $inscription_evenement);
        $form->handleRequest($request);
        if ($form->isSubmitted() and $form->isValid()) {
            $em = $this->getDoctrine()->getManager();
            $em->persist($inscription_evenement);
            $em->flush();
            $this->addFlash('success','ajoutée avec succée!');
            return $this->redirectToRoute('inscription_read');
        }
        return $this->render('@event/inscription_evenement/add.html.twig', array(
            'form' => $form->createView()

        ));
    }

    public function readAction()
    {
        $list = $this->getDoctrine()->getRepository(inscription_evenement::class)->findAll();
        return $this->render('@event/inscription_evenement/read.html.twig', array(
            'inscriptions' => $list

        ));
    }
}
