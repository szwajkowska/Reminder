# Reminder

Program, which allows to save and read notes. Each note contains title, body, date and time when it should be remained. 
At the right time user get the information about the proper note. When application is not running, all notes from the past which weren't remined are remained after the program start.

<b>Code example</b>
  
    private void saveNote(Note note) {
          String fileName = location + "/" + note.getTitle() + ".txt";
          if (!Files.exists(Paths.get(fileName))) {
              writeToFile(note, note.getTitle());
          } else {
              writeToFile(note, note.getTitle() + random.nextInt(1000));
          }
      }

      private void writeToFile(Note note, String fileName) {
          try {

            PrintWriter writer = new PrintWriter(new File(location + "/" + fileName + ".txt"));
            writer.println(note.getTitle() + "\n" + note.getBody() + "\n" + note.getDate()
                + "\n" + note.isWasReminded() + "\n" + note.getId());
            writer.close();
        } catch (FileNotFoundException fnfe) {
            fnfe.printStackTrace();
        }

    }
