using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class Week10RGBManager : MonoBehaviour
{
    public Slider R1Slider;
    public Slider G1Slider;
    public Slider B1Slider;

    public Text R1Value;
    public Text G1Value;
    public Text B1Value;

    public Slider R2Slider;
    public Slider G2Slider;
    public Slider B2Slider;

    public Text R2Value;
    public Text G2Value;
    public Text B2Value;

    public GameObject Sphere1;
    public GameObject Sphere2;
    public GameObject NewSphere;

    public Color Color1;
    public Color Color2;
    public Color NewColor;

    public Slider LerpSlider;
    public Text LerpValue;

    // Use this for initialization
    void Start ()
    {
		
	}
	
	// Update is called once per frame
	void Update ()
    {
        float r1 = R1Slider.value * 255f;
        float g1 = G1Slider.value * 255f;
        float b1 = B1Slider.value * 255f;

        int tempr1 = (int)r1;
        int tempg1 = (int)g1;
        int tempb1 = (int)b1;

        R1Value.text = (tempr1).ToString();
        G1Value.text = (tempg1).ToString();
        B1Value.text = (tempb1).ToString();

        Color1 = new Color(R1Slider.value, G1Slider.value, B1Slider.value);
        Sphere1.gameObject.transform.GetComponent<Renderer>().material.color = Color1;


        float r2 = R2Slider.value * 255f;
        float g2 = G2Slider.value * 255f;
        float b2 = B2Slider.value * 255f;

        int tempr2 = (int)r2;
        int tempg2 = (int)g2;
        int tempb2 = (int)b2;

        R2Value.text = (tempr2).ToString();
        G2Value.text = (tempg2).ToString();
        B2Value.text = (tempb2).ToString();

        Color2 = new Color(R2Slider.value, G2Slider.value, B2Slider.value);
        Sphere2.gameObject.transform.GetComponent<Renderer>().material.color = Color2;


        /* LerpCodeHere */////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        //How far between color1 and color2 are you supposed to be? Use the slider in the scene to see. Scale for lerping is 0 to 1.
        NewColor = Color.Lerp(Color1, Color2, LerpSlider.value);

        NewSphere.gameObject.transform.GetComponent<Renderer>().material.color = NewColor;
        LerpValue.text = ("LerpValue: " + LerpSlider.value);

    }
}
