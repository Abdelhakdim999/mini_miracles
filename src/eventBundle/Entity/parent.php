<?php

namespace eventBundle\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * parents
 *
 * @ORM\Table(name="parents")
 * @ORM\Entity(repositoryClass="eventBundle\Repository\parentsRepository")
 */
class parents


{
    /**
     * @var int
     *
     * @ORM\Column(name="idParent", type="integer")
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="AUTO")
     */
    private $idParent;

    /**
     * @var int
     *
     * @ORM\Column(name="nb_enfants", type="integer")
     */
    private $nbEnfants;

    /**
     *  Get id
     * @return int
     */
    public function getIdParent()
    {
        return $this->idParent;
    }

    /**
     * @param int $idParent
     */
    public function setIdParent($idParent)
    {
        $this->idParent = $idParent;
    }



    /**
     * Set nbEnfants
     *
     * @param integer $nbEnfants
     *
     * @return parents
     */
    public function setNbEnfants($nbEnfants)
    {
        $this->nbEnfants = $nbEnfants;

        return $this;
    }

    /**
     * Get nbEnfants
     *
     * @return int
     */
    public function getNbEnfants()
    {
        return $this->nbEnfants;
    }
}

