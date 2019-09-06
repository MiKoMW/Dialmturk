package com.github.mikomw.utli;

import com.alibaba.fastjson.JSON;
import com.github.mikomw.Dialogue.Dialogue;
import com.github.mikomw.Dialogue.DialogueUtterance;

import java.util.ArrayList;
import java.util.List;

public class DummyDialogueGenerator {

    /* Field for a Dialogue.
    private String _id;
    private String subId;
    private String userID;
    private String name_of_dialog;
    private String[] feedback;
    private List<DialogueUtterance> dialog;
*/

    public static Dialogue generateOneDialogue(String userID, Integer numOfDialogueUtterances, Integer lengthOfUtterance,boolean addEndBotUtterance){
        Dialogue dialogue = new Dialogue();
        dialogue.set_id(":)");
        dialogue.setSubId(":)");
        dialogue.setUserID(userID);
        dialogue.setName_of_dialog("Noobot");
        ArrayList<DialogueUtterance> dialogueUtterances = new ArrayList<>();
        for(int con = 0; con < numOfDialogueUtterances; con++){
            DialogueUtterance botDialogueUtterance =  new DialogueUtterance();
            DialogueUtterance userDialogueUtterance =  new DialogueUtterance();
            botDialogueUtterance.setRole("Bot");
            userDialogueUtterance.setRole("You");
            StringBuilder utteranceBuffer = new StringBuilder();
            for(int sub = 0; sub < lengthOfUtterance; sub++){
                if(sub == 0){
                    utteranceBuffer.append("Hi");
                }else {
                    utteranceBuffer.append(" Hi");
                }
            }
            utteranceBuffer.append(".");

            String utterance = utteranceBuffer.toString();
            botDialogueUtterance.setUtter(utterance);
            userDialogueUtterance.setUtter(utterance);
            dialogueUtterances.add(botDialogueUtterance);
            dialogueUtterances.add(userDialogueUtterance);
        }

        if(addEndBotUtterance) {
            DialogueUtterance botEndDialogueUtterance = new DialogueUtterance();
            botEndDialogueUtterance.setRole("Bot");
            botEndDialogueUtterance.setUtter("Bye.");
            dialogueUtterances.add(botEndDialogueUtterance);
        }
        dialogue.setDialog(dialogueUtterances);

        return dialogue;
    }

    public static List<Dialogue> generateListDialogues(String userID, Integer numOfDialogueUtterances, Integer lengthOfUtterance,boolean addEndBotUtterance, Integer numOfDialogues){
        List<Dialogue> ans = new ArrayList<>();
        for(int con = 0; con < numOfDialogues; con++){
            ans.add(generateOneDialogue(userID, numOfDialogueUtterances, lengthOfUtterance, addEndBotUtterance));
        }
        return ans;
    }

    public static void main(String[] args){

        Dialogue dialogue = DummyDialogueGenerator.generateOneDialogue("10086",10,10,true);
        System.out.println(JSON.toJSONString(dialogue,true));
    }

}

/*
A sample dialogue
{
    "_id": "5d2f0293fa798f0012eb2363",
    "subId": "5d2dbc0125693a0011ffe83b",
    "userID": "1563361935664",
    "name_of_dialog": "Noobot",
    "feedback": [],
    "dialog": [
      {
        "role": "You",
        "utter": "START"
      },
      {
        "role": "Bot",
        "utter": "Hello! (Portal just initialized a new session.)"
      },
      {
        "role": "You",
        "utter": " "
      },
      {
        "role": "Bot",
        "utter": "Sorry, I am just a toy!The 1 message:  "
      },
      {
        "role": "You",
        "utter": " "
      },
      {
        "role": "Bot",
        "utter": "Sorry, I am just a toy!The 2 message:  "
      },
      {
        "role": "You",
        "utter": " "
      },
      {
        "role": "Bot",
        "utter": "Sorry, I am just a toy!The 3 message:  "
      }
    ]
  },
 */