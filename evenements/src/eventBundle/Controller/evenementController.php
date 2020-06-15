<?php

namespace eventBundle\Controller;

use Doctrine\ORM\Query\AST\Functions\CurrentDateFunction;
use eventBundle\Form\inscription_evenementType;
use Symfony\Component\HttpFoundation\JsonResponse;
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
use Symfony\Component\Serializer\Normalizer\ObjectNormalizer;
use Symfony\Component\Serializer\Serializer;
use Symfony\Component\Validator\Validator\ValidatorInterface;
use Knp\Bundle\SnappyBundle\Snappy\Response\PdfResponse;



class evenementController extends Controller
{

    public function ajoutEvenementAction(Request $request)
    {
        $evenement = new evenement();
        $form = $this->createForm(evenementType::class, $evenement);
        $form->handleRequest($request);

        $datenow = (new \DateTime('now'));
       $date =  $evenement->getDateEvent();

        if ($form->isSubmitted() and $form->isValid() and $date > $datenow ) {




            $em = $this->getDoctrine()->getManager();
            //
            $file = $evenement->getPhoto();
            $fileName = md5(uniqid()) . '.' . $file->guessExtension();
            $file->move($this->getParameter('upload_directory'), $fileName);
            $evenement->setPhoto($fileName);
            //
            $em->persist($evenement);
            $em->flush();

            $this->addFlash('success', 'ajoutée avec succée!');
            return $this->redirectToRoute('evenement_read');

        }

        return $this->render('@event/evenement/ajouter.html.twig', array(
            'form' => $form->createView()
        ));
    }

    public function readAction(Request $request)
    {
        $em = $this->getDoctrine()->getRepository(evenement::class);
        $list = $em->findall();


        $paginator = $this->get('knp_paginator');

        $result = $paginator->paginate(
            $list,
            $request->query->getInt('page', 1),
            $request->query->getInt('limit', 3)

        );
        return $this->render('@event/evenement/read.html.twig', array('evenements' => $result));
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
            $file = $evenement->getPhoto();
            $fileName = md5(uniqid()) . '.' . $file->guessExtension();
            $file->move($this->getParameter('upload_directory'), $fileName);
            $evenement->setPhoto($fileName);
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
        $requestString = $request->get('q');
        $evenements = $em->getRepository('eventBundle:evenement')->findEntitiesByString($requestString);
        if (!$evenements) {
            $result['evenements']['error'] = "Event Not found :( ";
        } else {
            $result['evenements'] = $this->getRealEntities($evenements);
        }
        return new Response(json_encode($result));

    }


    public function readOneAction($id)
    {
        $em = $this->getDoctrine()->getRepository(evenement::class);
        $evenement = $em->find($id);

        return $this->render('@event/evenement/readOne.html.twig', array('evenement' => $evenement));
    }

    public function pdfAction($id)
    {







        $snappy = $this->get('knp_snappy.pdf');

        $em = $this->getDoctrine()->getRepository(evenement::class);
        $evenement = $em->find($id);

        $html = $this->renderView('@event/evenement/readOne.html.twig', array(
            'evenement' => $evenement
        ));

        $filename = 'myFirstSnappyPDF';

        return new Response(
            $snappy->getOutputFromHtml($html),
            200,
            array(
                'Content-Type'          => 'application/pdf',
                'Content-Disposition'   => 'inline; filename="'.$filename.'.pdf"'
            )
        );
    }

    public function readOneFrontAction($id)
    {
        $em = $this->getDoctrine()->getRepository(evenement::class);
        $evenement = $em->find($id);

        return $this->render('@event/evenement/FrontInscri.html.twig', array('evenement' => $evenement));
    }

    public function readOneFront2Action($id)
    {
        $em = $this->getDoctrine()->getRepository(evenement::class);
        $evenement = $em->find($id);
        return $this->render('@event/evenement/readOne2.html.twig', array('evenement' => $evenement));
    }

    public function readFrontAction(Request $request)
    {
        $em = $this->getDoctrine()->getRepository(evenement::class);
        $list = $em->myfindall();

        $paginator = $this->get('knp_paginator');

        $result = $paginator->paginate(
            $list,
            $request->query->getInt('page', 1),
            $request->query->getInt('limit', 3)
        );
        return $this->render('@event/evenement/readFront.html.twig', array('evenements' => $result));
    }

    public function readFrontmobileAction(Request $request)
    {
        $em = $this->getDoctrine()->getRepository(evenement::class);
        $list = $em->myfindall();
        foreach ($list as $ev) {
            $ev->setDateEvent($ev->getDateEvent()->format('Y-m-d H:i'));
        }
        $serializer = new Serializer([new ObjectNormalizer()]);
        $formatted = $serializer->normalize($list);
        return new JsonResponse($formatted);

        $paginator = $this->get('knp_paginator');

        $result = $paginator->paginate(
            $list,
            $request->query->getInt('page', 1),
            $request->query->getInt('limit', 3)
        );
        return $this->render('@event/evenement/readFrontmobile.html.twig', array('evenements' => $result));
    }

    public function inscriAddAction($id, Request $request)
    {

        $em = $this->getDoctrine()->getManager();
        $evenement = $em->getRepository(evenement::class)->find($id);


        return $this->render('@event/evenement/readOne.html.twig', array(
            'evenement' => $evenement
        ));


    }

    public function searchFrontAction(Request $request)
    {

        $em = $this->getDoctrine()->getManager();
        $evenements = $em->getRepository(evenement::class)->findAll();
        if ($request->isMethod('POST')) {
            $nom = $request->get('nom');
            $evenements = $em->getRepository(evenement::class)->findBy(array('nom' => $nom));

        }
        return $this->render('@event/evenement/readFront.html.twig',
            array('evenements' => $evenements));


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

    public function searchFAction(Request $request)
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

    public function getRealEntities($evenements)
    {
        foreach ($evenements as $evenements) {
            $realEntities[$evenements->getId()] = [$evenements->getNom()];

        }
        return $realEntities;
    }

    /* public function getRealEntities($rec)
     {
         foreach ($rec as $rec) {
             $realEntities[$rec->getId()] = [$rec->getLieu(), $rec->getNom(), $rec->getPrix() ,  $rec->getNbPlaces(),$rec->getDateEvent()];

         }
         return $realEntities;
     }*/

    public function affichFrontAction($id, Request $request)
    {


        //
        //$em= $this->getDoctrine()->getManager();
       // $user=$this->getUser();
        //$idu=$user->getId();
        //
        //
        //$enfants = $this->getDoctrine()->getRepository(enfant::class)->findAllEn($idu);
        //
        $em = $this->getDoctrine()->getManager();
        $evenement = $em->getRepository(evenement::class)->find($id);
        $inscription_evenement = new inscription_evenement();
        $form = $this->createForm(inscription_evenementType::class, $inscription_evenement);
        $form->handleRequest($request);
        $nom = $evenement->getNom();
        $prix = $evenement->getPrix();
        $nb = $evenement->getNbPlaces();


        if ($form->isSubmitted() and $form->isValid() and $nb>0) {

            $inscription_evenement->setIdevent($evenement);
            $inscription_evenement->setPrix($prix);
            $inscription_evenement->setDateInsc(new \DateTime('now'));

           // $inscription_evenement->setIdenfant($enfants);

            $em = $this->getDoctrine()->getManager();
            $em->persist($inscription_evenement);
            $nb = $evenement->setNbPlaces($nb - 1);
            $em->flush();

            $this->addFlash('success', 'ajoutée avec succée!');


            return $this->redirectToRoute('inscription_read');
        }
        return $this->render('@event/evenement/FrontInscri.html.twig', array(
            'form' => $form->createView(),
            'nom' => $nom, 'prix' => $prix,
        ));


    }

    public function affichFront2Action($id, Request $request)
    {

        $em = $this->getDoctrine()->getManager();
        $evenement = $em->getRepository(evenement::class)->find($id);
        $inscription_evenement = new inscription_evenement();
        $form = $this->createForm(inscription_evenementType::class, $inscription_evenement);
        $form->handleRequest($request);
        $nom = $evenement->getNom();
        $prix = $evenement->getPrix();
        $nb = $evenement->getNbPlaces();


        if ($form->isSubmitted() and $form->isValid()) {

            $inscription_evenement->setIdevent($evenement);
            $inscription_evenement->setPrix($prix);
            $inscription_evenement->setDateInsc(new \DateTime('now'));


            $em = $this->getDoctrine()->getManager();
            $em->persist($inscription_evenement);
            $nb = $evenement->setNbPlaces($nb - 1);
            $em->flush();

            $this->addFlash('success', 'ajoutée avec succée!');


            return $this->redirectToRoute('inscription_readInscEnf');
        }
        return $this->render('@event/evenement/FrontInscri2.html.twig', array(
            'form' => $form->createView(),
            'nom' => $nom, 'prix' => $prix,
        ));


    }


    public function readinscAction($id, Request $request)
    {
        $em = $this->getDoctrine()->getManager();
        $evenement = $em->getRepository(evenement::class)->find($id);

        $inscriptions = $this->getDoctrine()->getManager()
            ->getRepository(inscription_evenement::class)->find_event_inscr($id);



        $paginator = $this->get('knp_paginator');

        $result = $paginator->paginate(
            $inscriptions,
            $request->query->getInt('page', 1),
            $request->query->getInt('limit', 3)
        );
        return $this->render('@event/evenement/read_ins_event.html.twig', array('inscriptions' => $result));
    }

    public function readinsc2Action($id, Request $request)
    {
        $em = $this->getDoctrine()->getManager();
        $evenement = $em->getRepository(evenement::class)->find($id);

        $inscriptions = $this->getDoctrine()->getManager()
            ->getRepository(inscription_evenement::class)->find_event_inscr($id);

        $paginator = $this->get('knp_paginator');

        $result = $paginator->paginate(
            $inscriptions,
            $request->query->getInt('page', 1),
            $request->query->getInt('limit', 3)

        );
        return $this->render('@event/evenement/read_ins_event2.html.twig', array('inscriptions' => $inscriptions));
    }

    public function readEnfantAction()
    {
        $em = $this->getDoctrine()->getRepository(enfant::class);
        $list = $em->findAll();
        return $this->render('@event/evenement/FrontInscri.html.twig', array('enfants' => $list));

    }

    public function affichFrontmobileAction($id, Request $request)
    {


        $em = $this->getDoctrine()->getManager();
        $evenement = $em->getRepository(evenement::class)->find($id);
        $inscription_evenement = new inscription_evenement();


        $inscription_evenement->setIdevent($evenement);
        $inscription_evenement->setPrix($request->get('prix'));
        $inscription_evenement->setDateInsc(new \DateTime('now'));


        $em = $this->getDoctrine()->getManager();
        $em->persist($inscription_evenement);

        $em->flush();

        $serializer2 = new Serializer([new ObjectNormalizer()]);
        $formatted2 = $serializer2->normalize($inscription_evenement);
        return new JsonResponse($formatted2);
        $this->addFlash('success', 'ajoutée avec succée!');


    }
    public function newMAction(evenement $idEvent,enfant $id_enfant)
    {
        $inscription_evenement = new inscription_evenement();
        $nbr=$idEvent->getNbPlaces();
        $nbr--;
        $idEvent->setNbPlaces($nbr);
        $event = $this->getDoctrine()->getRepository(evenement::class)->find($idEvent);
        $userr = $this->getDoctrine()->getRepository(enfant::class)->find($id_enfant);
        $em = $this->getDoctrine()->getManager();
        $inscription_evenement->setIdEvent($event);
        $inscription_evenement->setIdenfant($userr);
        $inscription_evenement->setPrix($idEvent->getPrix());
        $inscription_evenement->setDateInsc(new \DateTime('now'));
    //    $i->setIdClient($userr);
        $em->persist($inscription_evenement);
        $em->flush();
        header('Content-type: application/json');
        return  new Response(json_encode( ["reponse"=>"oui"] ));
    }


    public function author(ValidatorInterface $validator)
    {
        $evenement= new evenement();

        // ... do something to the $author object

        $errors = $validator->validate($evenement);

        if (count($errors) > 0) {
            /*
             * Uses a __toString method on the $errors variable which is a
             * ConstraintViolationList object. This gives us a nice string
             * for debugging.
             */
            $errorsString = (string) $errors;

            return new Response($errorsString);


        }

        return new Response('The event is valid! Yes!');
    }

}


