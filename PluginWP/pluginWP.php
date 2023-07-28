<?php
/**
 * Plugin Name: Annoucments
 * Plugin URI: https://example.com/plugins/Header Message Display/
 * Description: Displays message while reload
 * Version: 1.0
 * Requires at least: 5.0
 * Requires PHP: 7.2
 * Author: Filip Justyna
 * Author URI: https://darksource.pl/
 * License: GPL v2 or later
 * License URI: https://www.gnu.org/licenses/gpl-2.0.html
 */

$ogloszenia = array();

function naph_admin_actions_register_menu()
{
    add_options_page("Annoucement", "Annoucement", 'manage_options', "naph", "naph_admin_page");
}

add_action('admin_menu', 'naph_admin_actions_register_menu');

function naph_admin_page() {
    $ogloszenia = get_option('ogloszenia', array());

    if (isset($_POST['naph_submit'])) {
        $nowe_ogloszenie = wp_kses_post($_POST['naph_ogloszenie']);
        array_push($ogloszenia, $nowe_ogloszenie);
        update_option('ogloszenia', $ogloszenia);
    }

    if (isset($_POST['naph_delete'])) {
        $ogloszenie_index = $_POST['naph_delete'];
        unset($ogloszenia[$ogloszenie_index]);
        update_option('ogloszenia', $ogloszenia);
    }

    if (count($ogloszenia) < 1) {
        echo '<p>Brak ogłoszeń.</p>';
    } else {
        echo '<ul>';
        foreach ($ogloszenia as $index => $ogloszenie) {
            echo '<li>';
            echo wpautop($ogloszenie);
            echo '<form method="post" action=""><input type="hidden" name="naph_delete" value="'.$index.'"><input type="submit" value="Usuń"></form>';
            echo '</li>';
        }
        echo '</ul>';
    }

    ?>
    <form method="post" action="">
        <?php wp_editor('', 'naph_ogloszenie', array('textarea_rows' => 5)); ?>
        <br>
        <input type="submit" name="naph_submit" value="Dodaj ogłoszenie">
    </form>
    <?php
}

function dodaj_ogloszenie_do_tresci($content)
{
    $przeczytaneOgloszenia = get_option('ogloszenia');

    $losowy_klucz = array_rand($przeczytaneOgloszenia);
    $losowe_ogloszenie = $przeczytaneOgloszenia[$losowy_klucz];

    return wpautop($losowe_ogloszenie) . $content;
}

add_filter("the_content", "dodaj_ogloszenie_do_tresci");
