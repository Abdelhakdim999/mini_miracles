<?php


namespace eventBundle\Controller;


    use Symfony\Component\HttpFoundation\RedirectResponse;
    use Symfony\Component\HttpFoundation\Request;
    use Symfony\Component\Routing\RouterInterface;
    use Symfony\Component\Security\Core\Authentication\Token\TokenInterface;
    use Symfony\Component\Security\Http\Authentication\AuthenticationSuccessHandlerInterface;
    use Symfony\Component\Security\Core\Authorization\AuthorizationChecker;
    use Symfony\Component\Security\Core\Exception\AccessDeniedException;

class securityController
{
    private $router;

    /**
     * AfterLoginRedirection constructor.
     *
     * @param RouterInterface $router
     */
    public function __construct(RouterInterface $router)
    {
        $this->router = $router;
    }

    /**
     * @return mixed
     * @Route ("/home")
     */

    public function redirectAction(){


        $authChecker = $this->container->get('security.authorization_checker');

        if ($authChecker->isGranted('ROLE_ADMIN')) {
            return $this->render('@event/security/indexFront.html.twig');
        }else if ($authChecker->isGranted('ROLE_USER')){
            return $this->render('@event/security/back.html.twig');

        }
        else{
            return $this->render('@FOSUser/security/login.html.twig');
        }

    }


}