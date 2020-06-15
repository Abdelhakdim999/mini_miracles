<?php

namespace eventBundle\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * pack
 *
 * @ORM\Table(name="pack")
 * @ORM\Entity(repositoryClass="eventBundle\Repository\packRepository")
 */
class pack
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
     * @var string
     *
     * @ORM\Column(name="titre", type="string", length=255)
     */
    private $titre;

    /**
     * @var string
     *
     * @ORM\Column(name="description", type="string", length=255)
     */
    private $description;

    /**
     * @var string
     *
     * @ORM\Column(name="type", type="string", length=255)
     */
    private $type;

    /**
     * @var int
     *
     * @ORM\Column(name="reduction", type="integer")
     */
    private $reduction;

    /**
     * @var string
     *
     * @ORM\Column(name="image", type="string", length=255)
     */
    private $image;

    /**
     * @var \DateTime
     *
     * @ORM\Column(name="duree_du", type="datetime")
     */
    private $dureeDu;

    /**
     * @var \DateTime
     *
     * @ORM\Column(name="duree_a", type="datetime")
     */
    private $dureeA;


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
     * Set titre
     *
     * @param string $titre
     *
     * @return pack
     */
    public function setTitre($titre)
    {
        $this->titre = $titre;

        return $this;
    }

    /**
     * Get titre
     *
     * @return string
     */
    public function getTitre()
    {
        return $this->titre;
    }

    /**
     * Set description
     *
     * @param string $description
     *
     * @return pack
     */
    public function setDescription($description)
    {
        $this->description = $description;

        return $this;
    }

    /**
     * Get description
     *
     * @return string
     */
    public function getDescription()
    {
        return $this->description;
    }

    /**
     * Set type
     *
     * @param string $type
     *
     * @return pack
     */
    public function setType($type)
    {
        $this->type = $type;

        return $this;
    }

    /**
     * Get type
     *
     * @return string
     */
    public function getType()
    {
        return $this->type;
    }

    /**
     * Set reduction
     *
     * @param integer $reduction
     *
     * @return pack
     */
    public function setReduction($reduction)
    {
        $this->reduction = $reduction;

        return $this;
    }

    /**
     * Get reduction
     *
     * @return int
     */
    public function getReduction()
    {
        return $this->reduction;
    }

    /**
     * Set image
     *
     * @param string $image
     *
     * @return pack
     */
    public function setImage($image)
    {
        $this->image = $image;

        return $this;
    }

    /**
     * Get image
     *
     * @return string
     */
    public function getImage()
    {
        return $this->image;
    }

    /**
     * Set dureeDu
     *
     * @param \DateTime $dureeDu
     *
     * @return pack
     */
    public function setDureeDu($dureeDu)
    {
        $this->dureeDu = $dureeDu;

        return $this;
    }

    /**
     * Get dureeDu
     *
     * @return \DateTime
     */
    public function getDureeDu()
    {
        return $this->dureeDu;
    }

    /**
     * Set dureeA
     *
     * @param \DateTime $dureeA
     *
     * @return pack
     */
    public function setDureeA($dureeA)
    {
        $this->dureeA = $dureeA;

        return $this;
    }

    /**
     * Get dureeA
     *
     * @return \DateTime
     */
    public function getDureeA()
    {
        return $this->dureeA;
    }
}

