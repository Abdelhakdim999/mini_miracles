<?php

namespace eventBundle\Controller;

use Symfony\Component\HttpFoundation\Response;
use eventBundle\Entity\enfant;
use eventBundle\Entity\evenement;
use eventBundle\Entity\inscription_evenement;
use eventBundle\Form\enfantType;
use eventBundle\Form\evenementType;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Symfony\Component\HttpFoundation\Request;
//use http\Client\Response;
use Sensio\Bundle\FrameworkExtraBundle\Configuration\Route;
use Doctrine\Common\Persistence\PersistentObject;
class evenementController extends Controller
{

    public function ajoutEvenementAction(Request $request)
    {
        $evenement = new evenement();
        $form = $this->createForm(evenementType::class, $evenement);
        $form->handleRequest($request);
        if ($form->isSubmitted() and $form->isValid()) {
            $em = $this->getDoctrine()->getManager();
            $em->persist($evenement);
            $em->flush();
            $this->addFlash('success', 'ajoutée avec succée!');
            return $this->redirectToRoute('evenement_read');

        }
        return $this->render('@event/evenement/ajouter.html.twig', array(
            'form' => $form->createView()
        ));
    }

    public function readAction()
    {
        $em = $this->getDoctrine()->getRepository(evenement::class);
        $list = $em->findall();
        return $this->render('@event/evenement/read.html.twig', array('evenements' => $list));
    }

    public function deleteAction($id)
    {
        $em = $this->getDoctrine()->getManager();
        $evenement = $em->getRepository(evenement::class)->find($id);
        $em->remove($evenement);
        $em->flush();
        return $this->redirectToRoute('evenement_read');

    }

    public function updateAction($id, Request $request)
    {
        $em = $this->getDoctrine()->getManager();
        $evenement = $em->getRepository(evenement::class)->find($id);
        $form = $this->createForm(evenementType::class, $evenement);
        $form = $form->handleRequest($request);
        if ($form->isValid()) {
            $em->flush();
            return $this->redirectToRoute('evenement_read');
        }
        return $this->render('@event/evenement/ajouter.html.twig', array(
            'form' => $form->createView()
        ));


    }
    public function searchAction(Request $request)
    {

        $em = $this->getDoctrine()->getManager();
       $evenements=$em->getRepository(evenement::class)->findAll();
      if($request->isMethod('POST')){
          $nom=$request->get('nom');
          $evenements=$em->getRepository(evenement::class)->findBy(array('nom'=>$nom));

        }
        return $this->render('@event/evenement/read.html.twig',
            array('evenements'=>$evenements));

    }

    public function readOneAction($id)
    {
        $em = $this->getDoctrine()->getRepository(evenement::class);
        $evenement = $em->find($id);
        return $this->render('@event/evenement/readOne.html.twig', array('evenement' => $evenement));
    }

    public function readFrontAction()
    {
        $em = $this->getDoctrine()->getRepository(evenement::class);
        $list = $em->myfindall();
        return $this->render('@event/evenement/readFront.html.twig', array('evenements' => $list));
    }

    public function inscriAddAction($id, Request $request){

        $em = $this->getDoctrine()->getManager();
        $evenement = $em->getRepository(evenement::class)->find($id);




        return $this->render('@event/evenement/readOne.html.twig', array(
            'evenement' => $evenement
        ));


    }

    public function searchFrontAction(Request $request)
    {

        $em = $this->getDoctrine()->getManager();
        $evenements=$em->getRepository(evenement::class)->findAll();
        if($request->isMethod('POST')){
            $nom=$request->get('nom');
            $evenements=$em->getRepository(evenement::class)->findBy(array('nom'=>$nom));

        }
        return $this->render('@event/evenement/readFront.html.twig',
            array('evenements'=>$evenements));


    }
    public function searchhAction(Request $request)
    {
        $em = $this->getDoctrine()->getManager();
        $libelle = $request->get('q');
        $rec = $em->getRepository('eventBundle:evenement')->SearchEvent($libelle);

        if (!$rec) {
            $result['evenement']['error'] = "Event does not exist :( ";
        } else {
            $result['evenement'] = $this->getRealEntities($rec);
        }
        return new Response(json_encode($result));
    }
    public function searchFronntAction(Request $request)
    {
        $em = $this->getDoctrine()->getManager();
        $libelle = $request->get('q');
        $rec = $em->getRepository('eventBundle:evenement')->SearchEvent($libelle);

        if (!$rec) {
            $result['evenement']['error'] = "Event does not exist :( ";
        } else {
            $result['evenement'] = $this->getRealEntities($rec);
        }
        return new Response(json_encode($result));
    }

    public function getRealEntities($rec)
    {
        foreach ($rec as $rec) {
            $realEntities[$rec->getId()] = [$rec->getLieu(), $rec->getNom(), $rec->getPrix() ,  $rec->getNbPlaces(),$rec->getDateEvent()];

        }
        return $realEntities;
    }

    public function affichFrontAction($id, Request $request){

        $em = $this->getDoctrine()->getManager();
        $evenement = $em->getRepository(evenement::class)->find($id);



        return $this->render('@event/evenement/FrontInscri.html.twig', array(
            'evenement' => $evenement

        ));


    }

    public function readEnfantAction()
    {
        $em = $this->getDoctrine()->getRepository(enfant::class);
        $list = $em->findAll();
        return $this->render('@event/evenement/FrontInscri.html.twig', array('enfants' => $list));
    }



}


