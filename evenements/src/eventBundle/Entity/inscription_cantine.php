<?php

namespace eventBundle\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * inscription_cantine
 *
 * @ORM\Table(name="inscription_cantine")
 * @ORM\Entity(repositoryClass="eventBundle\Repository\inscription_cantineRepository")
 */
class inscription_cantine
{
    /**
     * @var int
     *
     * @ORM\Column(name="id", type="integer")
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="AUTO")
     */
    private $id;

    /**
     * @ORM\ManyToOne(targetEntity="enfant")
     * @ORM\JoinColumn(name="idEnf", referencedColumnName="id")
     */
    private $idEnf;

    /**
     * @ORM\ManyToOne(targetEntity="cantine")
     * @ORM\JoinColumn(name="idCant", referencedColumnName="id")
     */
    private $idCant;

    /**
     * @var int
     *
     * @ORM\Column(name="nbrjour", type="integer")
     */
    private $nbrjour;

    /**
     * @var float
     *
     * @ORM\Column(name="prix", type="float")
     */
    private $prix;

    /**
     * @var \DateTime
     *
     * @ORM\Column(name="date_insc", type="datetime")
     */
    private $dateInsc;


    /**
     * Get id
     *
     * @return int
     */
    public function getId()
    {
        return $this->id;
    }

    /**
     * Set idEnf
     *
     * @param integer $idEnf
     *
     * @return inscription_cantine
     */
    public function setIdEnf($idEnf)
    {
        $this->idEnf = $idEnf;

        return $this;
    }

    /**
     * Get idEnf
     *
     * @return int
     */
    public function getIdEnf()
    {
        return $this->idEnf;
    }

    /**
     * Set idCant
     *
     * @param integer $idCant
     *
     * @return inscription_cantine
     */
    public function setIdCant($idCant)
    {
        $this->idCant = $idCant;

        return $this;
    }

    /**
     * Get idCant
     *
     * @return int
     */
    public function getIdCant()
    {
        return $this->idCant;
    }

    /**
     * Set nbrjour
     *
     * @param integer $nbrjour
     *
     * @return inscription_cantine
     */
    public function setNbrjour($nbrjour)
    {
        $this->nbrjour = $nbrjour;

        return $this;
    }

    /**
     * Get nbrjour
     *
     * @return int
     */
    public function getNbrjour()
    {
        return $this->nbrjour;
    }

    /**
     * Set prix
     *
     * @param float $prix
     *
     * @return inscription_cantine
     */
    public function setPrix($prix)
    {
        $this->prix = $prix;

        return $this;
    }

    /**
     * Get prix
     *
     * @return float
     */
    public function getPrix()
    {
        return $this->prix;
    }

    /**
     * Set dateInsc
     *
     * @param \DateTime $dateInsc
     *
     * @return inscription_cantine
     */
    public function setDateInsc($dateInsc)
    {
        $this->dateInsc = $dateInsc;

        return $this;
    }

    /**
     * Get dateInsc
     *
     * @return \DateTime
     */
    public function getDateInsc()
    {
        return $this->dateInsc;
    }
}
