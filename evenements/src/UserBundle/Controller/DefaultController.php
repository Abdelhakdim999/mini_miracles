<?php


namespace UserBundle\Controller;
use eventBundle\Entity\enfant;
use eventBundle\Entity\evenement;
use eventBundle\Entity\inscription_evenement;

use Symfony\Bundle\FrameworkBundle\Controller\Controller;


class DefaultController extends Controller
{
    public function indexAction()
    {
        $u = $this->container->get('security.token_storage')->getToken()->getUser();

        try {
            switch ($u->getRoles()[0]) {
                case "PARENT":
                    return $this->redirect('access/parent');
                    break;
                case "ADMIN":
                    return $this->redirect('access/admin');
                    break;

            }
        } catch (\Throwable $e) {
            return $this->redirect('http://localhost/evenements/web/app_dev.php/login');

        };
    }

    public function AdminAction()
    {
        //$user=$this->container->get('security.token_storage')->getToken()->getToken()->getUser()->getNom();

        $em= $this->getDoctrine()->getManager();
        $user=$this->getUser();
        $idu=$user->getId();
        $nomu=$user->getUsername();

        return $this->render('@User/Default/admin.html.twig', array(
            'u' => $user,
            'nom' => $nomu,
        ));

    }
    public function parentAction()
    {
       return $this->render('@User/Default/parent.html.twig');
    }


    public function homepageAction()
    {
        return $this->render('@User/Default/homepage.html.twig');

    }



}