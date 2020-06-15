<?php

namespace eventBundle\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * inscription_evenement
 *
 * @ORM\Table(name="inscription_evenement")
 * @ORM\Entity(repositoryClass="eventBundle\Repository\inscription_evenementRepository")
 */
class inscription_evenement
{
    /**
     * @var int
     *
     * @ORM\Column(name="id_insc", type="integer")
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="AUTO")
     */
    private $id_insc;

    /**
     * @var float
     *
     * @ORM\Column(name="prix", type="float", nullable=true)
     */
    private $prix;

    /**
     * @var \DateTime
     *
     * @ORM\Column(name="dateInsc", type="datetime")
     */
    private $dateInsc;

    /**
     * @ORM\ManyToOne(targetEntity="enfant")
     * @ORM\JoinColumn(name="id_enfant", referencedColumnName="id")
     */

    private $id_enfant;

    /**
     * @ORM\ManyToOne(targetEntity="evenement")
     * @ORM\JoinColumn(name="id_event", referencedColumnName="id")
     */

    private $id_event;

    /**
     * @param int $id_insc
     */
    public function setId_Insc($id_insc)
    {
        $this->id_insc = $id_insc;
    }



    /**
     * Get id
     *
     * @return int
     */
    public function getId_insc()
    {
        return $this->id_insc;
    }




    /**
     * Get id
     *
     * @return int
     */
    public function getIdInsc()
    {
        return $this->id_insc;
    }

    /**
     * Set prix
     *
     * @param float $prix
     *
     * @return inscription_evenement
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
     * @return mixed
     */
    public function getIdenfant()
    {
        return $this->id_enfant;
    }

    /**
     * @return mixed
     */
    public function getid_enfant()
    {
        return $this->id_enfant;
    }

    /**
     * @param mixed $id_enfant
     */
    public function setIdenfant($id_enfant)
    {
        $this->id_enfant = $id_enfant;
    }

    /**
     * @return mixed
     */
    public function getIdevent()
    {
        return $this->id_event;
    }
    /**
     * @return mixed
     */
    public function getid_event()
    {
        return $this->id_event;
    }

    /**
     * @param integer $id_event
     */
    public function setIdevent($id_event)
    {
        $this->id_event = $id_event;
    }





    /**
     * Set dateInsc
     *
     * @param \DateTime $dateInsc
     *
     * @return inscription_evenement
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

