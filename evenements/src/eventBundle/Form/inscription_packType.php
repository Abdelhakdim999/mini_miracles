<?php

namespace eventBundle\Form;

use eventBundle\Entity\enfant;
use eventBundle\Entity\pack;
use Symfony\Bridge\Doctrine\Form\Type\EntityType;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;

class inscription_packType extends AbstractType
{
    /**
     * {@inheritdoc}
     */
    public function buildForm(FormBuilderInterface $builder, array $options)
    {
        $builder->add('dateInsc')
            ->add('idEnfant',EntityType::class,
            array(
                'class'=>enfant::class,
                'choice_label'=>"prenom",
                'multiple'=>false))
            ->add('idPack',EntityType::class,
            array(
                'class'=>pack::class,
                'choice_label'=>"titre",
                'multiple'=>false));
    }
    /**
     * {@inheritdoc}
     */
    public function configureOptions(OptionsResolver $resolver)
    {
        $resolver->setDefaults(array(
            'data_class' => 'eventBundle\Entity\inscription_pack'
        ));
    }

    /**
     * {@inheritdoc}
     */
    public function getBlockPrefix()
    {
        return 'eventbundle_inscription_pack';
    }


}
