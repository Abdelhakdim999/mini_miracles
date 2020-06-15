<?php

namespace eventBundle\Form;

use eventBundle\Entity\sujet;
use Symfony\Bridge\Doctrine\Form\Type\EntityType;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;
class commentaireType extends AbstractType
{
    /**
     * {@inheritdoc}
     */
    public function buildForm(FormBuilderInterface $builder, array $options)
    {
        $builder->add('contenu')->add('date')->add('idUser')->add('idSujet',EntityType::class,
            array(
                'class'=>sujet::class,
                'choice_label'=>"contenu",
                'multiple'=>false
                /**   il ne peut pas afficher l'entité club donc on doit choisir un seul attribut par choice_label
                 * multiple=>false permet d'avoir un seul choix
                 */
            ));
    }/**
 * {@inheritdoc}
 */
    public function configureOptions(OptionsResolver $resolver)
    {
        $resolver->setDefaults(array(
            'data_class' => 'eventBundle\Entity\commentaire'
        ));
    }

    /**
     * {@inheritdoc}
     */
    public function getBlockPrefix()
    {
        return 'eventbundle_commentaire';
    }


}
