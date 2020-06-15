<?php

namespace eventBundle\Controller;

use eventBundle\Entity\evenement;
use eventBundle\Entity\inscription_evenement;
use eventBundle\Form\inscription_evenementType;
use Knp\Bundle\SnappyBundle\Snappy\Response\PdfResponse;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Symfony\Component\HttpFoundation\JsonResponse;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Serializer\Normalizer\ObjectNormalizer;
use Symfony\Component\Serializer\Serializer;

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

    public function readAction(Request $request)
    {
        $list = $this->getDoctrine()->getRepository(inscription_evenement::class)->findAllorder();

        $paginator = $this->get('knp_paginator');

        $result = $paginator->paginate(
            $list,
            $request->query->getInt('page', 1),
            $request->query->getInt('limit', 3)
        );


        return $this->render('@event/inscription_evenement/read.html.twig', array(
            'inscriptions' => $result

        ));
    }

    public function read2Action(Request $request)
    {
        $list = $this->getDoctrine()->getRepository(inscription_evenement::class)->findAllorder();


        return $this->render('@event/inscription_evenement/read2.html.twig', array(
            'inscriptions' => $list

        ));
    }

    public function readmobileAction(Request $request)
    {
        $list = $this->getDoctrine()->getRepository(inscription_evenement::class)->findAllorder();


        foreach ($list as $ev){
            $ev->setDateInsc($ev->getDateInsc()->format('Y-m-d H:i'));
        }


        $serializer = new Serializer([new ObjectNormalizer()]);
        $formatted = $serializer->normalize($list);
        return new JsonResponse($formatted);

        return $this->render('@event/inscription_evenement/readmobile.html.twig', array(
            'inscriptions' => $list

        ));
    }

    public function searchinsAction(Request $request)
    {
        $em = $this->getDoctrine()->getManager();
        $libelle = $request->get('q');
        $rec = $em->getRepository('eventBundle:inscription_evenement')->Searchinscri($libelle);

        if (!$rec) {
            $result['inscription_evenement']['error'] = "Inscription does not exist :( ";
        } else {
            $result['inscription_evenement'] = $this->getRealEntities($rec);
        }

        return new Response(json_encode($result));
    }
    public function getRealEntities($inscription_evenement){
        foreach ($inscription_evenement as $inscription_evenements){
            $realEntities[$inscription_evenements->getId_insc()] = [$inscription_evenements->getid_event()->getNom()];

        }
        return $realEntities;
    }

    public function readOneAction($id)
    {
        $em = $this->getDoctrine()->getRepository(inscription_evenement::class);
        $inscription_evenement = $em->find($id);

        return $this->render('@event/inscription_evenement/readOne.html.twig', array('inscription_evenement' => $inscription_evenement));
    }




    public function readinscAction()
    {
        $inscriptions = $this->getDoctrine()->getManager()
            ->getRepository(inscription_evenement::class)->findAll();
        return $this->render('@event/inscription_evenement/read_ins.html.twig', array('inscriptions' => $inscriptions));
    }



    public function pdfAction($id)
    {

        $em = $this->getDoctrine()->getRepository(inscription_evenement::class);
        $ins = $em->find($id);
        $html = $this->renderView('eventBundle:inscription_evenement:pdf_facture.html.twig', array(
            'inscription_evenement'  => $ins
        ));

        return new PdfResponse(
            $this->get('knp_snappy.pdf')->getOutputFromHtml($html),
            'file.pdf'
        );
    }


    public function readInscEnfAction(Request $request)
    {

        $em= $this->getDoctrine()->getManager();
        $user=$this->getUser();
        $idu=$user->getId();
        $nomu=$user->getUsername();
        $list = $this->getDoctrine()->getRepository(inscription_evenement::class)->findAllEnforder($idu);


        $paginator = $this->get('knp_paginator');

        $result = $paginator->paginate(
            $list,
            $request->query->getInt('page', 1),
            $request->query->getInt('limit', 3)
        );

        return $this->render('@event/inscription_evenement/readinscenf.html.twig', array(
            'inscriptions' => $result

        ));
    }


    public function deleteAction($id)
    {
        $em = $this->getDoctrine()->getManager();
        $inscription_evenement = $em->getRepository(inscription_evenement::class)->find($id);

        $nb = $inscription_evenement->getid_event()->getNbPlaces();
        $em->remove($inscription_evenement);

        $nb = $inscription_evenement->getid_event()->setNbPlaces($nb + 1);
        $em->flush();
        return $this->redirectToRoute('inscription_read');

    }

    public function remplir_combo_enfAction()
    {
        $em = $this->getDoctrine()->getManager();
        $user = $this->getUser();
        $idu = $user->getId();


        $myenfants = $this->getDoctrine()->getRepository(enfant::class)->findAllEn($idu);


        if (!$myenfants) {
            $result['inscription_evenement']['error'] = "Inscription does not exist :( ";
        } else {
            $result['inscription_evenement'] = $this->getRealEntities($myenfants);
        }


        return new Response(json_encode($result));
    }

    public function getRealEntitiesenf($myenfants){
        foreach ($myenfants as $myenfant){
            $realEntities[$myenfant->getId] = [$myenfant->getPrenom()];

        }
        return $realEntities;
    }
}
