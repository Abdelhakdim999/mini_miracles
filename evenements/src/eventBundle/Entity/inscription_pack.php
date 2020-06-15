<?php

namespace eventBundle\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * inscription_pack
 *
 * @ORM\Table(name="inscription_pack")
 * @ORM\Entity(repositoryClass="eventBundle\Repository\inscription_packRepository")
 */
class inscription_pack
{
    /**
     * @var int
     *
     * @ORM\Column(name="id", type="integer")
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="AUTO")
     */

    private $idInscription;

    /**
     * @var \DateTime
     *
     * @ORM\Column(name="date_insc", type="datetime")
     */
    private $dateInsc;

    /**
     * @ORM\ManyToOne(targetEntity="enfant")
     * @ORM\JoinColumn(name="idEnfant", referencedColumnName="id")
     */
    private $idEnfant;

    /**
     * @ORM\ManyToOne(targetEntity="pack")
     * @ORM\JoinColumn(name="idPack", referencedColumnName="id")
     */
    private $idPack;




    /**
     * Get idInscription
     *
     * @return integer
     */
    public function getIdInscription()
    {
        return $this->idInscription;
    }

    /**
     * Set dateInsc
     *
     * @param \DateTime $dateInsc
     *
     * @return inscription_pack
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

    /**
     * Set idEnfant
     *
     * @param integer $idEnfant
     *
     * @return inscription_pack
     */
    public function setIdEnfant($idEnfant)
    {
        $this->idEnfant = $idEnfant;

        return $this;
    }

    /**
     * Get idEnfant
     *
     * @return int
     */
    public function getIdEnfant()
    {
        return $this->idEnfant;
    }

    /**
     * Set idPack
     *
     * @param integer $idPack
     *
     * @return inscription_pack
     */
    public function setIdPack($idPack)
    {
        $this->idPack = $idPack;

        return $this;
    }

    /**
     * Get idPack
     *
     * @return int
     */
    public function getIdPack()
    {
        return $this->idPack;
    }
}
