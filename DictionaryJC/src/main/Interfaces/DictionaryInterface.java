package main.Interfaces;

import java.io.IOException;

public interface DictionaryInterface {
    void showDictionary() throws IOException;
    void  findEntryInDictionary() throws IOException;
    void  makeEntryInDictionary();
    void  deleteEntryInDictionary();

}