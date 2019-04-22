using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class SoundSliderLoader : MonoBehaviour
{
    private Music music;

    private void Start()
    {
        music = gameObject.GetComponentInParent<Music>();
        loadSliderValue();
    }

    public void loadSliderValue()
    {
        Slider slider = gameObject.GetComponent<Slider>();

        float volume;
         music.audioMixer.GetFloat("volume", out volume);
        Debug.Log(volume);

        slider.value = volume;
    }
}
