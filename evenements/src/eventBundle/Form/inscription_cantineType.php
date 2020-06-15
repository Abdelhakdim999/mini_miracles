<?php

namespace eventBundle\Form;
use eventBundle\Entity\enfant;
use eventBundle\Entity\cantine;
use Symfony\Bridge\Doctrine\Form\Type\EntityType;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;

class inscription_cantineType extends AbstractType
{
    /**
     * {@inheritdoc}
     */
    public function buildForm(FormBuilderInterface $builder, array $options)
    {
        $builder->add('nbrjour')->add('prix')->add('dateInsc')->add('idEnf',EntityType::class,
            array(
                'class'=>enfant::class,
                'choice_label'=>"prenom",
                'multiple'=>false
                /**   il ne peut pas afficher l'entité enfant donc on doit choisir un seul attribut par choice_label
                 * multiple=>false permet d'avoir un seul choix
                 */
            ))->add('idCant',EntityType::class,
            array(
                'class'=>cantine::class,
                'choice_label'=>"id",
                'multiple'=>false
                /**   il ne peut pas afficher l'entité enfant donc on doit choisir un seul attribut par choice_label
                 * multiple=>false permet d'avoir un seul choix
                 */
            ));
    }/**
 * {@inheritdoc}
 */
    public function configureOptions(OptionsResolver $resolver)
    {
        $resolver->setDefaults(array(
            'data_class' => 'eventBundle\Entity\inscription_cantine'
        ));
    }

    /**
     * {@inheritdoc}
     */
    public function getBlockPrefix()
    {
        return 'eventbundle_inscription_cantine';
    }


}
