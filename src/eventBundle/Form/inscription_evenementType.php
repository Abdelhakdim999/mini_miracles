<?php

namespace eventBundle\Form;

use eventBundle\Entity\enfant;
use eventBundle\Entity\evenement;
use Symfony\Bridge\Doctrine\Form\Type\EntityType;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;

class inscription_evenementType extends AbstractType
{
    /**
     * {@inheritdoc}
     */
    public function buildForm(FormBuilderInterface $builder, array $options)
    {
        $builder->add('prix')->add('dateInsc')
            ->add('enfant',
                EntityType::class,
                array('class'=>enfant::class,
                    'choice_label'=>'nom',
                'multiple'=>false))
            ->add('evenement',
                EntityType::class,
                array('class'=>evenement::class,
                    'choice_label'=>'nom',
                'multiple'=>false));
    }

    /**
     * {@inheritdoc}
     */
    public function configureOptions(OptionsResolver $resolver)
    {
        $resolver->setDefaults(array(
            'data_class' => 'eventBundle\Entity\inscription_evenement'
        ));
    }

    /**
     * {@inheritdoc}
     */
    public function getBlockPrefix()
    {
        return 'eventbundle_inscription_evenement';
    }


}
