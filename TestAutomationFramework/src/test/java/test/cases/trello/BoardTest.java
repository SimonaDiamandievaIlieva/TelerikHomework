package test.cases.trello;

import org.junit.After;
import org.junit.Before;
import pages.trello.BoardPage;
import pages.trello.BoardsPage;
import org.junit.Test;


public class BoardTest extends BaseTest {

    @Before
    public void BeforeTests() {
        login();

        BoardsPage boardsPage = new BoardsPage(actions.getDriver());
        boardsPage.createBoard();
    }

    @After
    public void AfterTests(){
        BoardPage boardPage = new BoardPage(actions.getDriver());
        boardPage.logout();
    }

    @Test
    public void createBoardWhenCreateBoardClicked() {

        BoardPage boardPage = new BoardPage(actions.getDriver());
        boardPage.assertAddListExists();

        boardPage.assertListExists("To Do");
        boardPage.deleteExistingBoard();
    }

    @Test
    public void createNewCardInExistingBoardWhenCreateCardClicked() {

        BoardPage boardPage = new BoardPage(actions.getDriver());
        boardPage.addCardToList("My First Card");
        boardPage.assertCardIsCreated("My First Card");
        boardPage.deleteExistingBoard();

    }

    @Test
    public void moveCardBetweenStatesWhenDragAndDropIsUsed() {

        BoardPage boardPage = new BoardPage(actions.getDriver());
        boardPage.addCardToList("My First Card");
        boardPage.moveCardToList("My First Card", "Doing");
        boardPage.assertCardIsUnderList("Doing", "My First Card");
        boardPage.deleteExistingBoard();
    }


    @Test
    public void deleteBoardWhenDeleteButtonIsClicked() {

        BoardPage boardPage = new BoardPage(actions.getDriver());
        boardPage.deleteExistingBoard();
        boardPage.assertBoardIsDeleted("My First Board");
    }
}
