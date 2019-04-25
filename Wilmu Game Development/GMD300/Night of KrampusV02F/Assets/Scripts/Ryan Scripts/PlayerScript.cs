using UnityEngine;
using System.Collections;

public class PlayerScript : MonoBehaviour {
	float horizontal_movement;
	public float speed = .5f;
	public float walkSpeed = 2;
	public float runSpeed = 4;
	public float jumpHeight = 5;
	public float jumpHorizDistance = 1;
	public float terminalVelocity = 20;
	float startingX;
	float endingX;
	float startingY;
	float endingY;
	bool touchingGround;
	bool touchingWall;
	bool wallSliding;
	bool testHeight;
	public bool isJumping;
	Vector3 dirVec;
	public Sprite tile; // Used to determine jump size per tiles.
	Animator anim;
	private bool waitaframe = true;
	
	// Audio Clips
	public AudioClip jumpSound;
	public AudioClip jumpSound2;
	public AudioClip jumpSound3;
	public AudioClip toWallJumpSound;
	public AudioClip wallJumpSound;
	
	// Use this for initialization
	void Start () {
		//Debug.Log ("gravity = " + Physics2D.gravity.ToString() + ".");
		
		dirVec = new Vector3 ();
		dirVec = Vector3.zero;
		testHeight = true;
		isJumping = false;
		
		anim = GetComponent<Animator> ();
		/*
		anim.SetBool ("isWallSliding", false);
		anim.SetBool ("isJumping", false);
		anim.SetBool ("isWalking", false);
		anim.SetBool ("isRunning", false);
		anim.SetBool ("isIdling", true);
		*/
	}
	
	// Update is called once per frame
	void FixedUpdate () {
		if (isJumping) 
		{
			anim.Play("santa_jump 0");
		}
		// Insert goatse.cx joke here.
		// Debug.Log ("Player velocity= " + GetComponent<Rigidbody2D>().velocity.ToString ());
		//Debug.Log ("touchingGround = " + touchingGround.ToString ());
		//Debug.Log ("touchingWall = " + touchingWall.ToString ());
		//Debug.Log ("wallSliding = " + wallSliding.ToString ());
		
		// Check falling direction
		if ((!touchingGround) && testHeight && (GetComponent<Rigidbody2D> ().velocity.y < 0)) 
		{
			endingY = gameObject.transform.position.y;
			//Debug.Log ("Tile Size.Y = " + tile.bounds.size.y.ToString ());
			//Debug.Log ("Jump height = " + Mathf.Abs ((endingY - startingY)/tile.bounds.size.y).ToString ());
			testHeight = false;
		}
		
		// Limit falling speed
		/*if (GetComponent<Rigidbody2D> ().velocity.magnitude >= terminalVelocity) 
		{
			GetComponent<Rigidbody2D>().velocity = GetComponent<Rigidbody2D>().velocity.normalized * terminalVelocity;
		}
		*/
		// Speed up when shift key is pressed
		if (Input.GetKey (KeyCode.LeftShift)) {
			speed = runSpeed;
		}
		
		// Standard ground jump
		if ((Input.GetKey (KeyCode.Space)) && touchingGround) {
			
			if (waitaframe == true)
			{
				int random = Random.Range(0, 2);	
				if (random == 0)
				{
					if (jumpSound != null) {
						AudioSource.PlayClipAtPoint (jumpSound, Vector3.zero);
						waitaframe = false;
					}
				}
				
				if (random == 1)
				{
					if (jumpSound2 != null) {
						AudioSource.PlayClipAtPoint (jumpSound2, Vector3.zero);
						waitaframe = false;
					}
				}
			}
			
			else
			{
				waitaframe = true;
			}
			
			// Jump
			startingX = transform.position.x;
			startingY = transform.position.y;
			isJumping = true;
			//GetComponent<Rigidbody2D> ().velocity = new Vector2 (0, jumpHeight + GetComponent<Rigidbody2D> ().position.y);
			GetComponent<Rigidbody2D> ().velocity = new Vector2 (0, jumpHeight);
			
			// Set animation to jumping
			/*
			anim.SetBool ("isWallSliding", false);
			anim.SetBool ("isWalking", false);
			anim.SetBool ("isRunning", false);
			anim.SetBool ("isIdling", false);
			anim.SetBool ("isJumping", true);
			*/
			anim.Play ("santa_jump 0");
			touchingGround = false;
			
			
			
		}
		// Wall sliding
		//else if (touchingWall && (Input.GetAxis ("Horizontal") == 0) && (!Input.GetKey (KeyCode.Space)))
		else if (touchingWall && (!Input.GetKey (KeyCode.Space))) 
		{
			// Set animation to wall sliding
			/*
			anim.SetBool ("isJumping", false);
			anim.SetBool ("isWalking", false);
			anim.SetBool ("isRunning", false);
			anim.SetBool ("isIdling", false);
			anim.SetBool ("isWallSliding", true);
			*/
			anim.Play ("santa_wallslide 0");
			
			// Set Wall sliding to true
			wallSliding = true;
			
			// Add "friction" to slide
			if (GetComponent<Rigidbody2D> ().velocity.magnitude >= 1f)
			{
				GetComponent<Rigidbody2D>().velocity = GetComponent<Rigidbody2D>().velocity.normalized * 1f;
			}
		}
		// Standard wall jump
		else if (Input.GetKey (KeyCode.Space) && wallSliding) {
			// Wall jump
			GetComponent<Rigidbody2D> ().velocity = new Vector2 (-transform.right.x * jumpHorizDistance, jumpHeight);
			//GetComponent<Rigidbody2D> ().AddForce(new Vector2 (-transform.right.x * jumpHorizDistance * 100, jumpHeight * 100));
			wallSliding = false;
			isJumping = true;
			
			if (waitaframe == true)
			{
				int random = Random.Range(0, 2);	
				if (random == 0)
				{
					if (jumpSound != null) {
						AudioSource.PlayClipAtPoint (jumpSound, Vector3.zero);
						waitaframe = false;
					}
				}
				
				if (random == 1)
				{
					if (jumpSound2 != null) {
						AudioSource.PlayClipAtPoint (jumpSound2, Vector3.zero);
						waitaframe = false;
					}
				}
			}
			
			else
			{
				waitaframe = true;
			}
			
			// Set animation to jumping
			/*
			anim.SetBool ("isWallSliding", false);
			anim.SetBool ("isWalking", false);
			anim.SetBool ("isRunning", false);
			anim.SetBool ("isIdling", false);
			anim.SetBool ("isJumping", true);
			*/
			anim.Play ("santa_jump 0");
			
		}
		// Regular movement
		else {
			horizontal_movement = Input.GetAxis ("Horizontal");
			
			if( Mathf.Abs(horizontal_movement) > 0)
			{
				if (speed == walkSpeed)
				{
					// Do movement animation
					/*
					anim.SetBool ("isIdling", false);
					anim.SetBool ("isRunning", false);
					anim.SetBool ("isWallSliding", false);
					anim.SetBool ("isJumping", false);
					anim.SetBool ("isWalking", true);
					*/
					if (!isJumping)
					{
					anim.Play ("santa_walk 0");
					}
				}
				else
				{
					// Do movement animation
					/*
					anim.SetBool ("isIdling", false);
					anim.SetBool ("isWallSliding", false);
					anim.SetBool ("isJumping", false);
					anim.SetBool ("isWalking", false);
					anim.SetBool ("isRunning", true);
					*/
					if (!isJumping)
					{
					anim.Play ("santa_run 0");
					}
				}
			}
			else
			{
				// Do idle animation
				/*
				anim.SetBool ("isWallSliding", false);
				anim.SetBool ("isJumping", false);
				anim.SetBool ("isWalking", false);
				anim.SetBool ("isRunning", false);
				anim.SetBool ("isIdling", true);
				*/
				if (!isJumping)
				{
				anim.Play ("santa_idle 0");
				}
			}
			
			dirVec.x = (horizontal_movement == 0) ? dirVec.x : Mathf.Sign (horizontal_movement);
			transform.right = dirVec; // Mathf.Sign (horizontal_movement);
			transform.Translate (speed * Mathf.Abs (horizontal_movement) * Time.fixedDeltaTime, 0, 0);
			speed = walkSpeed;
		}
	}
	
	void OnCollisionExit2D(Collision2D other)
	{
		if (other.gameObject.tag == "floor_coll") 
		{
			touchingGround = false;
		}
		
		if (other.gameObject.tag == "wall_coll") 
		{
			if (wallSliding)
			{
				wallSliding = false;
			}
			
			touchingWall = false;
		}
	}
	
	void OnCollisionEnter2D(Collision2D other)
	{
		if (other.gameObject.tag == "floor_coll") 
		{
			if (touchingGround == false)
			{
				endingX = transform.position.x;
				//Debug.Log ("Jump length = " + Mathf.Abs ((endingX - startingX)/tile.bounds.size.x).ToString ());
			}
			if (isJumping)
			{
				isJumping = false;
				anim.SetBool ("isJumping", false);
			}
			touchingGround = true;
			testHeight = true;
		}
		
		if (other.gameObject.tag == "wall_coll") 
		{
			if (isJumping)
			{
				isJumping = false;
				anim.SetBool ("isJumping", false);
			}
			touchingWall = true;
		}
	}
	
	void OnCollisionStay2D(Collision2D other)
	{
		if (other.gameObject.tag == "floor_coll") 
		{
			touchingGround = true;
		}
		
		if (other.gameObject.tag == "wall_coll") 
		{
			if (touchingGround)
			{
				touchingWall = false;
			}
			else
			{
				touchingWall = true;
			}
		}
	}
}
