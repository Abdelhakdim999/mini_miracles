<?php

namespace eventBundle\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * cantine
 *
 * @ORM\Table(name="cantine")
 * @ORM\Entity(repositoryClass="eventBundle\Repository\cantineRepository")
 */
class cantine
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
     * @ORM\Column(name="heurouv", type="string", length=255, nullable=true)
     */
    private $heurouv;

    /**
     * @var string
     *
     * @ORM\Column(name="heurferm", type="string", length=255, nullable=true)
     */
    private $heurferm;

    /**
     * @var int
     *
     * @ORM\Column(name="capacite", type="integer", nullable=true)
     */
    private $capacite;


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
     * Set heurouv
     *
     * @param string $heurouv
     *
     * @return cantine
     */
    public function setHeurouv($heurouv)
    {
        $this->heurouv = $heurouv;

        return $this;
    }

    /**
     * Get heurouv
     *
     * @return string
     */
    public function getHeurouv()
    {
        return $this->heurouv;
    }

    /**
     * Set heurferm
     *
     * @param string $heurferm
     *
     * @return cantine
     */
    public function setHeurferm($heurferm)
    {
        $this->heurferm = $heurferm;

        return $this;
    }

    /**
     * Get heurferm
     *
     * @return string
     */
    public function getHeurferm()
    {
        return $this->heurferm;
    }

    /**
     * Set capacite
     *
     * @param integer $capacite
     *
     * @return cantine
     */
    public function setCapacite($capacite)
    {
        $this->capacite = $capacite;

        return $this;
    }

    /**
     * Get capacite
     *
     * @return int
     */
    public function getCapacite()
    {
        return $this->capacite;
    }
}

