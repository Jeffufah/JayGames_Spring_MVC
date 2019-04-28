using UnityEngine;
using System.Collections;
using UnityEngine.UI;
using UnityEngine.EventSystems;

public class ChangeStationScript : MonoBehaviour
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


                int NewPosition;

                int.TryParse(gameObject.GetComponent<InputField>().text, out NewPosition);

                if ((NewPosition >= 0) || (NewPosition <= 6))
                {
                    StartCoroutine(ChangePositionThenRetrievePosition(NewPosition));
                }
            }
            //NameTargetInputField
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

    IEnumerator ChangePositionThenRetrievePosition(int NewPosition)
    {
        yield return ChangePosition(NewPosition);
        yield return RetrievePosition();
    }

    IEnumerator ChangePosition(int NewPosition)
    {
        yield return GameObject.Find(GameObject.Find("NameTargetInputField").gameObject.transform.GetComponent<InputField>().text).gameObject.transform.GetComponent<PositionRelayedWeb>().WritePositionCommand(GameObject.Find("NameTargetInputField").gameObject.transform.GetComponent<InputField>().text, NewPosition);
    }

    IEnumerator RetrievePosition()
    {
        GameObject.Find(GameObject.Find("NameTargetInputField").gameObject.transform.GetComponent<InputField>().text).gameObject.transform.GetComponent<PositionRelayedWeb>().CallRetrievePositionCommand();
        temporaryInputFieldText = "";
        GameObject.Find("NameInputCanvas").gameObject.transform.GetComponent<DisplayHideNameInputCanvas>().SetOnOrOff();
        return null;
    }
}
