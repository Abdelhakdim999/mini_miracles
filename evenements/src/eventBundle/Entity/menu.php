<?php

namespace eventBundle\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * menu
 *
 * @ORM\Table(name="menu")
 * @ORM\Entity(repositoryClass="eventBundle\Repository\menuRepository")
 */
class menu
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
     * @ORM\Column(name="entre", type="string", length=255)
     */
    private $entre;

    /**
     * @var string
     *
     * @ORM\Column(name="plat", type="string", length=255)
     */
    private $plat;

    /**
     * @var string
     *
     * @ORM\Column(name="dessert", type="string", length=255)
     */
    private $dessert;

    /**
     * @var \DateTime
     *
     * @ORM\Column(name="date", type="datetime")
     */
    private $date;


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
     * Set entre
     *
     * @param string $entre
     *
     * @return menu
     */
    public function setEntre($entre)
    {
        $this->entre = $entre;

        return $this;
    }

    /**
     * Get entre
     *
     * @return string
     */
    public function getEntre()
    {
        return $this->entre;
    }

    /**
     * Set plat
     *
     * @param string $plat
     *
     * @return menu
     */
    public function setPlat($plat)
    {
        $this->plat = $plat;

        return $this;
    }

    /**
     * Get plat
     *
     * @return string
     */
    public function getPlat()
    {
        return $this->plat;
    }

    /**
     * Set dessert
     *
     * @param string $dessert
     *
     * @return menu
     */
    public function setDessert($dessert)
    {
        $this->dessert = $dessert;

        return $this;
    }

    /**
     * Get dessert
     *
     * @return string
     */
    public function getDessert()
    {
        return $this->dessert;
    }

    /**
     * Set date
     *
     * @param \DateTime $date
     *
     * @return menu
     */
    public function setDate($date)
    {
        $this->date = $date;

        return $this;
    }

    /**
     * Get date
     *
     * @return \DateTime
     */
    public function getDate()
    {
        return $this->date;
    }
}

