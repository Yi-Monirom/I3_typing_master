package audio;

import java.io.IOException;
import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class AudioPlayer {

	public static int MENU = 0;
	public static int PLAYING = 1;

	public static int DIE = 0;
	public static int DIE_2 = 1;
	public static int MISSED_ME = 2;
	public static int JUMP = 3;
	public static int I_wont_lose = 4;

	private Clip[] songs, naruto_effect, sakura_effect, sasuke_effect, animal_effects;

	private int currentSongId, currentAnimal_SongId, current_EffectId;

	public static int snake_hissing = 0;
	public static int snake_attack = 1;
	public static int wolf_attack = 2;
	public static int wolf_growl = 3;

	public AudioPlayer() {
		loadSongs();
		loadEffects();
		loadAnimalSound();
		playSong(MENU);
		stopEffects();
	}

	public void playSong(int song) {

		currentSongId = song;
		songs[currentSongId].setMicrosecondPosition(0);
		songs[currentSongId].loop(Clip.LOOP_CONTINUOUSLY);
		setVolume(songs[currentSongId], 0.60f);
		songs[currentSongId].start();
	}

	public void stopEffects() {

		if (sakura_effect[current_EffectId].isActive()) {
			sakura_effect[current_EffectId].stop();
		}
		if (sasuke_effect[current_EffectId].isActive()) {
			sasuke_effect[current_EffectId].stop();
		}
		if (naruto_effect[current_EffectId].isActive()) {
			naruto_effect[current_EffectId].stop();
		}

	}

	public void plaEffects(int select, int effect) {

		current_EffectId = effect;
		if (select == 0) {
			sakura_effect[current_EffectId].setMicrosecondPosition(0);
			setVolume(sakura_effect[current_EffectId], 0.65f);
			sakura_effect[current_EffectId].start();
		} else if (select == 1) {
			naruto_effect[current_EffectId].setMicrosecondPosition(0);
			setVolume(naruto_effect[current_EffectId], 0.75f);
			naruto_effect[current_EffectId].start();
		} else if (select == 2) {
			sasuke_effect[current_EffectId].setMicrosecondPosition(0);
			setVolume(sasuke_effect[current_EffectId], 0.75f);
			sasuke_effect[current_EffectId].start();
		}

	}

	public void stopSong() {
		if (songs[currentSongId].isActive())
			songs[currentSongId].stop();

	}

	public void stop_Animal_sound() {
		if (animal_effects[currentAnimal_SongId].isActive())
			animal_effects[currentAnimal_SongId].stop();
	}

	public void play_Animal_sound(int sound) {
		stop_Animal_sound();

		currentAnimal_SongId = sound;
		animal_effects[currentAnimal_SongId].setMicrosecondPosition(0);
		animal_effects[currentAnimal_SongId].loop(Clip.LOOP_CONTINUOUSLY);
		setVolume(animal_effects[currentAnimal_SongId], 0.65f);
		animal_effects[currentAnimal_SongId].start();
	}

	public void setVolume(Clip clip, float volume) {
		if (clip != null) {
			try {
				FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
				float min = gainControl.getMinimum();
				float max = gainControl.getMaximum();
				float newVolume = min + (max - min) * volume; // Scale volume (0.0 to 1.0)
				gainControl.setValue(newVolume);
			} catch (IllegalArgumentException e) {
				System.out.println("Volume control not supported for this clip.");
			}
		}
	}

	public void playingSong() {
		stopSong();
		playSong(PLAYING);
	}

	private void loadEffects() {
		String[] effectNaruto = { "naruto_dead_1", "uzumaki-naruto", "miss_me", "Voicy_Anime Jump", "I_won't_lose" };
		naruto_effect = new Clip[effectNaruto.length];
		for (int i = 0; i < naruto_effect.length; i++) {
			naruto_effect[i] = getClip(effectNaruto[i]);
		}
		String[] effectSakura = { "Am_I_done_for", "Sakura_see-i-dont-always-need-protecting", "Sakura_jump",
				"Voicy_Anime Jump", "Sakura_see-i-dont-always-need-protecting" };
		sakura_effect = new Clip[effectSakura.length];
		for (int i = 0; i < sakura_effect.length; i++) {
			sakura_effect[i] = getClip(effectSakura[i]);
		}
		String[] effectSasuke = { "Sasuke_dead2", "Sasuke_restore-the-uchiha-clan", "Sasuke_jump",
				"Voicy_Anime Jump", "Sasuke_i'm_not_done_yet" };
		sasuke_effect = new Clip[effectSasuke.length];
		for (int i = 0; i < sasuke_effect.length; i++) {
			sasuke_effect[i] = getClip(effectSasuke[i]);
		}

	}

	private void loadAnimalSound() {
		String[] effectNames = { "snake_hissing", "snake-attack", "wolf-attack", "growl-dog-wolf" };
		animal_effects = new Clip[effectNames.length];
		for (int i = 0; i < animal_effects.length; i++)
			animal_effects[i] = getClip(effectNames[i]);

	}

	private void loadSongs() {
		String[] names = { "narutos-theme-song", "playing" };
		songs = new Clip[names.length];
		for (int i = 0; i < songs.length; i++)
			songs[i] = getClip(names[i]);
	}

	private Clip getClip(String name) {
		URL url = getClass().getResource("/newAudio/" + name + ".wav");
		AudioInputStream audio;

		try {
			audio = AudioSystem.getAudioInputStream(url);
			Clip c = AudioSystem.getClip();
			c.open(audio);
			return c;

		} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {

			e.printStackTrace();

		}
		return null;
	}
	// private void udateSongVolume() {
	// //FloatControl gainControl=(FloatControl) songs
	// }
}
