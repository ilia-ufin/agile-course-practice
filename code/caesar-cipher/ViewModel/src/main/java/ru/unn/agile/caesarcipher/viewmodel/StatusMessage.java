package ru.unn.agile.caesarcipher.viewmodel;

public class StatusMessage {

   public enum Status {
       READY("Correct input"),
       BAD_INPUT("Input correct value"),
       SUCCESSFUL("Successful"),
       LIMITED_DIGIT("You can enter up to 12 digits"),
       WAITING("Waiting");

   private final String description;

   Status(final String description) {
       this.description = description;
   }

   public String getDescription() {
       return description;
   }
   };
}
