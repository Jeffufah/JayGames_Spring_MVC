using UnityEngine;
using System.Collections;
using UnityEngine.UI;
using UnityEngine.EventSystems;

public class ChangeNameScript : MonoBehaviour
{
    public string temporaryInputFieldText;

    public void Start()
    {
        EventSystem.current.SetSelectedGameObject(gameObject);

        gameObject.GetComponent<InputField>().onValueChanged.AddListener(delegate { });

        temporaryInputFieldText = gameObject.GetComponent<InputField>().text.ToString();
    }

    public void WhenEditEnded()
    {
        if (Input.GetKeyDown(KeyCode.Return))
        {
            if (temporaryInputFieldText != gameObject.GetComponent<InputField>().text)
            {
                temporaryInputFieldText = gameObject.GetComponent<InputField>().text.ToString();
                StartCoroutine(ChangeNameThenRetrieveName(gameObject.GetComponent<InputField>().text));
            }

            else
            {
                gameObject.GetComponent<InputField>().text = temporaryInputFieldText;
            }
        }

        else
        {
            if (temporaryInputFieldText != gameObject.GetComponent<InputField>().text)
            {
                gameObject.GetComponent<InputField>().text = temporaryInputFieldText;
            }
        }
    }

    // Update is called once per frame
    void Update()
    {

    }

    IEnumerator ChangeNameThenRetrieveName(string NewName)
    {
        yield return ChangeName(NewName);
        yield return RetrieveName();
    }

    IEnumerator ChangeName(string NewName)
    {
        yield return GameObject.Find(GameObject.Find("NameTargetInputField").gameObject.transform.GetComponent<InputField>().text).gameObject.transform.GetComponent<PositionRelayedWeb>().ChangeNameCommand(NewName);

    }

    IEnumerator RetrieveName()
    {
        yield return GameObject.Find(GameObject.Find("NameTargetInputField").gameObject.transform.GetComponent<InputField>().text).gameObject.transform.GetComponent<PositionRelayedWeb>().RetrieveName();
        temporaryInputFieldText = "";
        GameObject.Find("NameInputCanvas").gameObject.transform.GetComponent<DisplayHideNameInputCanvas>().SetOnOrOff();
    }
}
