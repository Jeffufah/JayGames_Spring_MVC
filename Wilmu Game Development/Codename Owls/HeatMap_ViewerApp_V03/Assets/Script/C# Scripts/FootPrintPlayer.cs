using UnityEngine;
using System.Collections;


public class FootPrintPlayer : MonoBehaviour
{

    private Renderer movieRender;

    public Material[] movieMaterial;

    public MovieTexture[] movie;

    public AudioClip[] movieAudio;


    public int currentMovie = 0;

    void Awake()
    {
  



    }

    void Start()
    {

        //movie [0]= (MovieTexture)movieRender.material.mainTexture;

        movieRender = GameObject.Find("MovieWall").GetComponent<Renderer>();




        /*
        for (int i = 0; i < movie.Length; i++)
        {
            movie[i].loop = true;
        }


        movieRender.material = movieMaterial[currentMovie];

        */
        movieRender.material = movieMaterial[currentMovie];

        movie[currentMovie].Play();
        

    }
}
