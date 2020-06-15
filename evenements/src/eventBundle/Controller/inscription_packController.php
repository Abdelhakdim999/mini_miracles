<?php

namespace eventBundle\Controller;

use eventBundle\Entity\inscription_pack;
use eventBundle\Form\inscription_packType;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Sensio\Bundle\FrameworkExtraBundle\Configuration\Method;
use Sensio\Bundle\FrameworkExtraBundle\Configuration\Route;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\HttpFoundation\Request;


/**
 * Inscription_pack controller.
 *
 * @Route("inscription_pack")
 */
class inscription_packController extends Controller
{
    /**
     * Lists all inscription_pack entities.
     *
     * @Route("/", name="inscription_pack_index")
     * @Method("GET")
     */
    public function indexAction(Request $request)
    {
        $em = $this->getDoctrine()->getManager();

        $inscription_packs = $em->getRepository('eventBundle:inscription_pack')->findAllOrderedBydate();
        $paginator = $this->get('knp_paginator');

        $result = $paginator->paginate(
            $inscription_packs,
            $request->query->getInt('page', 1),
            $request->query->getInt('limit', 3)
        );

        return $this->render('@event/inscription_pack/index.html.twig', array(
            'inscription_packs' => $result,
        ));
    }

    /**
     * Creates a new inscription_pack entity.
     *
     * @Route("/new", name="inscription_pack_new")
     * @Method({"GET", "POST"})
     */
    public function newAction(Request $request)
    {

        $em = $this->getDoctrine()->getManager();
        $user=$this->getUser();
        $idu=$user->getId();
        $myenfant = $em->getRepository('eventBundle:enfant')->findAllEn($idu);


        $inscription_pack= new Inscription_pack();
        $form = $this->createForm('eventBundle\Form\inscription_packType', $inscription_pack);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()){
            $em = $this->getDoctrine()->getManager();
            $em->persist($inscription_pack);
            $em->flush();

            return $this->redirectToRoute('inscription_pack_show', array('idInscription' => $inscription_pack->getIdInscription()));
        }

        return $this->render('@event/inscription_pack/new.html.twig', array(
            'inscription_pack' => $inscription_pack,
            'form' => $form->createView(),
            'myenfants'=>$myenfant,
        ));


    }


    /**
     * Finds and displays a inscription_pack entity.
     *
     * @Route("/{idInscription}", name="inscription_pack_show")
     * @Method("GET")
     */
    public function showFAction(Request $request)
    {
        $em = $this->getDoctrine()->getManager();

        $inscription_packs = $em->getRepository('eventBundle:inscription_pack')->findAllOrderedBydate();
        $paginator = $this->get('knp_paginator');

        $result = $paginator->paginate(
            $inscription_packs,
            $request->query->getInt('page', 1),
            $request->query->getInt('limit', 3)
        );


        return $this->render('@event/inscription_pack/indexFront.html.twig', array(
            'inscription_packs' => $result,
        ));
    }

    /**
     * Displays a form to edit an existing inscription_pack entity.
     *
     * @Route("/{idInscription}/edit", name="inscription_pack_edit")
     * @Method({"GET", "POST"})
     */
    public function editAction(Request $request, inscription_pack $inscription_pack)
    {

        $editForm = $this->createForm('eventBundle\Form\inscription_packType', $inscription_pack);
        $editForm->handleRequest($request);

        if ($editForm->isSubmitted() && $editForm->isValid()) {
            $this->getDoctrine()->getManager()->flush();

            return $this->redirectToRoute('inscription_pack_index', array('idInscription' => $inscription_pack->getIdinscription()));
        }

        return $this->render('@event/inscription_pack/edit.html.twig', array(
            'inscription_pack' => $inscription_pack,
            'edit_form' => $editForm->createView(),
        ));
    }

    /**
     * Deletes a inscription_pack entity.
     *
     * @Route("/{idInscription}", name="inscription_pack_delete")
    $em = $this->getDoctrine()->getManager();
    $inscription_pack=$em->getRepository(inscription_pack::class)->find($inscription_pack);
    $inscription_pack->getIdInscription();
    $em->remove($inscription_pack);
    $em->flush();
     * @Method("DELETE")
     */
    public function deleteAction(inscription_pack $inscription_pack)
    {

        $em = $this->getDoctrine()->getManager();
        $pack=$em->getRepository(inscription_pack::class)->find($inscription_pack);
        $inscription_pack->getIdInscription();
        $em->remove($inscription_pack);
        $em->flush();
        return $this->redirectToRoute('inscription_pack_index');
    }

    public function search22Action(Request $request)
    {
        $em = $this->getDoctrine()->getManager();
        $libelle = $request->get('qi');
        $rec = $em->getRepository('eventBundle:inscription_pack')->Search1inscri($libelle);

        if (!$rec) {
            $result['inscription_pack']['error'] = "Inscription introuvable :( ";
        } else {
            $result['inscription_pack'] = $rec;
        }

        return new Response(json_encode($result));
    }
    public function getRealEntities($inscription_pack){
        foreach ($inscription_pack as $inscription_pack){
            $realEntities[$inscription_pack->getIdInscription()] = [$inscription_pack-> getIdEnfant()->getNom() ];

        }
        return $realEntities;
    }



    // la partie front


    public function readinscFAction(Request $request)
    {
        $em = $this->getDoctrine()->getManager();

        $inscription_packs = $em->getRepository('eventBundle:inscription_pack')->findAllOrderedBydate();
        $paginator = $this->get('knp_paginator');

        $result = $paginator->paginate(
            $inscription_packs,
            $request->query->getInt('page', 1),
            $request->query->getInt('limit', 3)
        );

        return $this->render('@event/inscription_pack/indexFront.html.twig', array(
            'inscription_packs' => $result,
        ));
    }

    public function inscriAddAction(Request $request)
      {
          $inscription_pack = new Inscription_pack();
          $form = $this->createForm('eventBundle\Form\inscription_packType', $inscription_pack);
          $form->handleRequest($request);
          $user=$this->getUser();


          if ($form->isSubmitted() && $form->isValid()) {
              $em = $this->getDoctrine()->getManager();

              $em->persist($inscription_pack);
              $em->flush();

          /**    $transport = (new \Swift_SmtpTransport('smtp.gmail.com',465,'ssl'))->setUsername("eyacherif136@gmail.com")->setPassword("eyacherif136");
              $mailer=new \Swift_Mailer($transport);

              $message = \Swift_Message::newInstance()
                  ->setSubject('Registration mail')
                  ->setFrom('meryemgomri@gmail.com')
                  ->setTo($user->getEmail())
                  ->setBody(
                      $this->renderView(
                      // app/Resources/views/Emails/registration.html.twig
                          '@event/inscription_pack/registration.html.twig',array(
                              'user'=>$user,
                          )
                      ),
                      'text/html'
                  );

              $mailer->send($message);
           **/

              $this->addFlash('success', 'ajoutée avec succée!');
                  return $this->redirectToRoute('inscription_pack_showF', array('idInscription' => $inscription_pack->getIdInscription()));
          }

          return $this->render('@event/inscription_pack/newInscriptionFront.html.twig', array(
              'inscription_pack' => $inscription_pack,
              'form' => $form->createView(),
          ));
      }




}
