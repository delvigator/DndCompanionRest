// let board = [
//     null, 0, null, 1, null, 2, null, 3,
//     4, null, 5, null, 6, null, 7, null,
//     null, 8, null, 9, null, 10, null, 11,
//     null, null, null, null, null, null, null, null,
//     null, null, null, null, null, null, null, null,
//     12, null, 13, null, 14, null, 15, null,
//     null, 16, null, 17, null, 18, null, 19,
//     20, null, 21, null, 22, null, 23, null
// ]
const boardId=[
    "a8","b8","c8","d8","e8","f8","g8","h8",
    "a7","b7","c7","d7","e7","f7","g7","h7",
    "a6","b6","c6","d6","e6","f6","g6","h6",
    "a5","b5","c5","d5","e5","f5","g5","h5",
    "a4","b4","c4","d4","e4","f4","g4","h4",
    "a3","b3","c3","d3","e3","f3","g3","h3",
    "a2","b2","c2","d2","e2","f2","g2","h2",
    "a1","b1","c1","d1","e1","f1","g1","h1",
]
let board = new Array(64);
class Checker {
    id
    indexOfBoard
    availableSpaces
    availableJumpSpaces
    isKing
    constructor(id, indexOfBoard) {
        this.availableSpaces = new Set(),
            this.availableJumpSpaces = new Set(),
            this.id = id,
            this.isKing = false;
        this.indexOfBoard = indexOfBoard;
    }

}

 board = [
    null, new Checker(0), null, new Checker(1), null, new Checker(2), null, new Checker(3),
    new Checker(4), null, new Checker(5), null, new Checker(6), null, new Checker(7), null,
    null, new Checker(8), null, new Checker(9), null, new Checker(10), null, new Checker(11),
    null, null, null, null, null, null, null, null,
    null, null, null, null, null, null, null, null,
    new Checker(12), null, new Checker(13), null, new Checker(14), null, new Checker(15), null,
    null, new  Checker(16), null, new Checker(17), null, new Checker(18), null, new Checker(19),
    new Checker(20), null, new  Checker(21), null, new  Checker(22), null, new Checker(23), null
]

function getBoard() {
    for (let i = 0; i < board.length; i++) {
        if(board[i]!=null){
        board[i].indexOfBoard=i;}

        
    }
}
getBoard();
let boardBeforeEnd = new Array(board.length);
function getBoardBeforeEnd(){
for (let i = 0; i < board.length; i++) {
    if(board[i]!=null)
    boardBeforeEnd[i] = new Checker(board[i].id,board[i].indexOfBoard)
    else(boardBeforeEnd[i]=null)
}
}
getBoardBeforeEnd();


let findPiece = function (checkId) {
    for(const value of board){
        if(value!=null && value.id==checkId)
        return value;
    }
    return null;
};

let greenColor = "greenyellow";
let redColor = "#b92828";
let blackChecker = [...document.getElementsByClassName("blackChecker"), ...document.getElementsByClassName("blackChecker-king")];
let whiteChecker = [...document.getElementsByClassName("whiteChecker"), ...document.getElementsByClassName("whiteChecker-king")];
let cells = document.querySelectorAll("td");


let whiteTurnText = document.querySelector(".white-turn-text");
let blackTurntext = document.querySelector(".black-turn-text");
let winText = document.getElementById("win");
let winDisplay = document.querySelector(".win-text");
let endText = document.querySelector(".end input");
let cancelText = document.querySelector(".cancel input");
let partsText = document.getElementById("text");
let partsInput = document.getElementById("textInput");

let player = 1;
let whiteScore = 12;
let blackScore = 12;
let whiteScoreBeforeEnd = 12;
let blackScoreBeforeEnd = 12;
let playerChecker;
// для определения состояния хода/не хода
let isMove = false;
//запись хода
let parts = "";
let partsBeforeEnd="";

let selectedChecker = null;

function parse(){
    let moveError='';
 let text=partsInput.value;
 var moves=text.split(" ");
 try{
 for(let i=0;i<moves.length;i++){
    if(moves[i]!=""){
        moveError=moves[i];
        let id;
        if(moves[i].indexOf("-")!=-1){
        id=moves[i].split("-");
        let idBefore=boardId.indexOf(id[0]);
        let idAfter=boardId.indexOf(id[1]);
        selectedChecker=board[idBefore];
        isCheckerKing();
        makeMove(idAfter-idBefore);
        end();
        }
        else {
            console.log(moves[i]);
            id=moves[i].split(":");
            let nextId=[];
            if(i+1<moves.length)
            nextId=moves[i+1].split(":");
            let nextChecker=null;
            if(nextId.length>0) nextChecker=nextId[0];
            console.log(id[0]);
            let idBefore=boardId.indexOf(id[0]);
            let idAfter=boardId.indexOf(id[1]);
            console.log(idBefore);
            selectedChecker=board[idBefore];
            if (selectedChecker==null) throw "invalid id";
            isCheckerKing();
            makeMove(idAfter-idBefore);
            if(idAfter>=12 && nextChecker!=null && nextChecker<12){ 
                console.log("ЧЕРНАЯ. СЛЕДУЮЩАЯ БЕЛАЯ");
                end();}
            if(idAfter<12 && nextChecker!=null && nextChecker>=12) {
                console.log("Белая. СЛЕДУЮЩАЯ ЧЕРНАЯ");
                end();}
           
        }

   
    // let idNow=boardId.indexOf(id[1]);
    // let cellFirst=document.getElementById(id[0]);
    
    // let indexFirst=[].indexOf.call(cells, cellFirst);
    
    // let indexSecond=[].indexOf.call(cells, document.getElementById(id[1]));
    // let checkerOnBoardFirst=board[indexFirst];
    // let checkerOnBoardSecond=board[indexSecond];

    // checkerOnBoardSecond=new Checker(checkerOnBoardFirst.id,idNow);
    // checkerOnBoardFirst=null;
    // board[indexSecond]=checkerOnBoardSecond;
    // board[indexFirst]=null;
    


    // let checkerFirst=cellFirst.innerHTML;
    // document.getElementById(id[1]).innerHTML=checkerFirst;
    // cellFirst.innerHTML="";
    // whiteChecker = [...document.getElementsByClassName("whiteChecker"), ...document.getElementsByClassName("whiteChecker-king")];
    // blackChecker = [...document.getElementsByClassName("blackChecker"), ...document.getElementsByClassName("blackChecker-king")];
    }

 }
}catch(err){
partsInput.value=partsInput.value+" !!!!В ЭТОМ ХОДЕ ДОПУЩЕНА ОШИБКА ->  "+moveError+"   !!!!";
}
 //setListener();
}
function cancel(id, indexOfBoard) {
    isMove = false;
    partsBeforeEnd="";
    for (let i = 0; i < board.length; i++) {
        if(boardBeforeEnd[i]!=null)
        {
        board[i] = new Checker(boardBeforeEnd[i].id,boardBeforeEnd[i].indexOfBoard);
        }
         else{board[i]=null}
    }
    blackScore = blackScoreBeforeEnd;
    whiteScore = whiteScoreBeforeEnd;
    resetSelectedChecker();
    removeCellonclick();
    removeEventListeners();
    removeCellsSelection();
    if (document.getElementById(id).classList.contains("whiteChecker-king")) {
        cells[indexOfBoard].innerHTML = '<div class="whiteChecker-king" id="' + id + '"}>';
       // board[indexOfBoard].isKing=true;
    }
    if (document.getElementById(id).classList.contains("blackChecker-king")) {
        cells[indexOfBoard].innerHTML = '<div class="blackChecker-king" id="' + id + '"}>';
       // board[indexOfBoard].isKing=true;
    }
    for (let i = 0; i < board.length; i++) {
        if (board[i] != null && board[i].id < 12 &&
            document.getElementById(board[i].id) != null &&
            document.getElementById(board[i].id).classList.contains("whiteChecker-king")
        ) {
            cells[i].innerHTML = '<div class="whiteChecker-king" id="' + board[i].id + '"}>';
          //  board[i].isKing=true;
        }
        else if (board[i] != null && board[i].id < 12) {
            cells[i].innerHTML = '<div class="whiteChecker" id="' + board[i].id + '"}>';
          //  board[i].isKing=true;
        }
        else if (board[i] != null && board[i].id >= 12 &&
            document.getElementById(board[i].id) != null &&
            document.getElementById(board[i].id).classList.contains("blackChecker-king")
        ) {
            cells[i].innerHTML = '<div class="blackChecker-king" id="' + board[i].id + '"}>';
           // board[i].isKing=true;
        }
        else if (board[i] != null && board[i].id >= 12) {
            cells[i].innerHTML = '<div class="blackChecker" id="' + board[i].id + '"}>';
        }
        else {
            cells[i].innerHTML = '';
        }
    }
    blackChecker = [...document.getElementsByClassName("blackChecker"), ...document.getElementsByClassName("blackChecker-king")];
    whiteChecker = [...document.getElementsByClassName("whiteChecker"), ...document.getElementsByClassName("whiteChecker-king")];
    cells = document.querySelectorAll("td");
    endText.removeAttribute("onclick");
    endText.removeAttribute("style");
    cancelText.removeAttribute("onclick");
    cancelText.removeAttribute("style");
    setListener();
}

function end() {
    isMove = false;
    parts+=partsBeforeEnd+" ";
    partsBeforeEnd="";
    partsText.value = parts;
    for (let i = 0; i < board.length; i++) {
        if(board[i]!=null){
        boardBeforeEnd[i] = new Checker(board[i].id,board[i].indexOfBoard);
        }
        else {boardBeforeEnd[i]=null;}
    }
    blackScoreBeforeEnd = blackScore;
    whiteScoreBeforeEnd = whiteScore;
    removeCellonclick();
    removeEventListeners();
    removeCellsSelection();
    for (let i = 0; i < board.length; i++) {
        if (board[i] != null && board[i].id < 12 &&
            document.getElementById(board[i].id).classList.contains("whiteChecker-king")
        ) {
            cells[i].innerHTML = '<div class="whiteChecker-king" id="' + board[i].id + '"}>';
            board[i].isKing=true;
        }
        else if (board[i] != null && board[i].id < 12) {
            cells[i].innerHTML = '<div class="whiteChecker" id="' + board[i].id + '"}>';
        }
        else if (board[i] != null && board[i].id >= 12 &&
            document.getElementById(board[i].id).classList.contains("blackChecker-king")
        ) {
            cells[i].innerHTML = '<div class="blackChecker-king" id="' + board[i].id + '"}>';
            board[i].isKing=true;
        }
        else if (board[i] != null && board[i].id >= 12) {
            cells[i].innerHTML = '<div class="blackChecker" id="' + board[i].id + '"}>';
        }
        else {
            cells[i].innerHTML = '';
        }
    }
    blackChecker = [...document.getElementsByClassName("blackChecker"), ...document.getElementsByClassName("blackChecker-king")];
    whiteChecker = [...document.getElementsByClassName("whiteChecker"), ...document.getElementsByClassName("whiteChecker-king")];
    cells = document.querySelectorAll("td");
    resetSelectedChecker();
    changePlayer();
    endText.removeAttribute("onclick");
    endText.removeAttribute("style");
    cancelText.removeAttribute("onclick");
    cancelText.removeAttribute("style");
}

function firstExample() {
    isMove = false;
    parts = "";
    partsText.value = parts;
    board = [
        null, new Checker(0), null, null, null, null, null, null,
        null, null, new Checker(2), null, new Checker(3), null, null, null,
        null, null, null, null, null, null, null, new Checker(4),
        null, null, new Checker(5), null, null, null, null, null,
        null, null, null, null, null, new Checker(12), null, new Checker(13),
        null, null, null, null, null, null, null, null,
        null, null, null, null, null, null, null, null,
        null, null, new Checker(1), null, null, null, null, null
    ];
    getBoard();
    getBoardBeforeEnd();
    whiteTurnText.style.display = "block";
    blackTurntext.style.display = "none";
    winDisplay.style.display = "none";
    
    whiteScore = 6;
    blackScore = 2;
    blackScoreBeforeEnd=whiteScore;
    whiteScoreBeforeEnd=whiteScore;
    resetSelectedChecker();
    removeCellonclick();
    removeEventListeners();
    removeCellsSelection();
    for (let i = 0; i < board.length; i++) {
        if (board[i] != null && board[i].id < 12) {
            cells[i].innerHTML = '<div class="whiteChecker" id="' + board[i].id + '"}>';
        }
        else if (board[i] != null && board[i].id >= 12) {
            cells[i].innerHTML = '<div class="blackChecker" id="' + board[i].id + '"}>';
        }
        else {
            cells[i].innerHTML = '';
        }
        if (board[i]!=null && board[i].id == 1) {
            cells[i].innerHTML = '<div class="whiteChecker-king" id="' + board[i].id + '"}>';
            board[i].isKing=true;
        }
    }
    blackChecker = [...document.getElementsByClassName("blackChecker"), ...document.getElementsByClassName("blackChecker-king")];
    whiteChecker = [...document.getElementsByClassName("whiteChecker"), ...document.getElementsByClassName("whiteChecker-king")];
    cells = document.querySelectorAll("td");
    player = 0;
    setListener();

}

function start() {
    isMove = false;
    parts = "";
    partsText.value = parts;
    board = [
        null, new Checker(0), null, new Checker(1), null, new Checker(2), null, new Checker(3),
        new Checker(4), null, new Checker(5), null, new Checker(6), null, new Checker(7), null,
        null, new Checker(8), null, new Checker(9), null, new Checker(10), null, new Checker(11),
        null, null, null, null, null, null, null, null,
        null, null, null, null, null, null, null, null,
        new Checker(12), null, new Checker(13), null, new Checker(14), null, new Checker(15), null,
        null, new  Checker(16), null, new Checker(17), null, new Checker(18), null, new Checker(19),
        new Checker(20), null, new  Checker(21), null, new  Checker(22), null, new Checker(23), null
    ]
    getBoard();
    getBoardBeforeEnd();
    whiteTurnText.style.display = "block";
    blackTurntext.style.display = "none";
    winDisplay.style.display="none";
    whiteScore = 12;
    blackScore = 12;
    blackScoreBeforeEnd=whiteScore;
    whiteScoreBeforeEnd=whiteScore;
    resetSelectedChecker();
    removeCellonclick();
    removeEventListeners();
    removeCellsSelection();
    for (let i = 0; i < board.length; i++) {
        if (board[i] != null && board[i].id < 12) {
            cells[i].innerHTML = '<div class="whiteChecker" id="' + board[i].id + '"}>';
        }
        else if (board[i] != null && board[i].id >= 12) {
            cells[i].innerHTML = '<div class="blackChecker" id="' + board[i].id + '"}>';
        }
        else {
            cells[i].innerHTML = '';
        }
    }
    blackChecker = [...document.getElementsByClassName("blackChecker"), ...document.getElementsByClassName("blackChecker-king")];
    whiteChecker = [...document.getElementsByClassName("whiteChecker"), ...document.getElementsByClassName("whiteChecker-king")];
    cells = document.querySelectorAll("td");
    player = 0;
    setListener();
}


function resetSelectedChecker() {
    if(selectedChecker!=null){
        selectedChecker.availableSpaces = new Set(),
        selectedChecker.availableJumpSpaces = new Set()
        selectedChecker=null;
    }
}

setListener();

function setListener() {
    if (player) {
        for (const value of blackChecker) {
            value.addEventListener('click', getPlayerCheckers);
        }
        playerChecker = blackChecker;

    } else {
        for (const value of whiteChecker) {
            value.addEventListener('click', getPlayerCheckers);
        } playerChecker = whiteChecker;
    }
    checkJumpSpaceForAll();

}

function getPlayerCheckers() {
    if (player) {
        playerChecker = whiteChecker;
    } else {
        playerChecker = blackChecker;
    }
    removeCellonclick();
    getSelectedChecker();
}
function removeCellsSelection() {
    for (const value of cells) {
        value.removeAttribute("style");
    }
}
function removeCellonclick() {
    for (const value of cells) {
        value.removeAttribute("onclick");
    }
}



function getSelectedChecker() {
     if (selectedChecker==null) {
        event.target.style.background = "yellow";
        selectedChecker=findPiece(event.target.id);
        isCheckerKing();
        getAvailableSpaces(isMove);
        checkCheckerConditions();
    }
    else if (selectedChecker.id == parseInt(event.target.id)) {
        event.target.removeAttribute("style");
        resetSelectedChecker();
        removeCellsSelection();
        checkJumpSpaceForAll();
    }
   

}

function isCheckerKing() {
    console.log(selectedChecker);
    if (document.getElementById(selectedChecker.id).classList.contains("whiteChecker-king") ||
        document.getElementById(selectedChecker.id).classList.contains("blackChecker-king")) {
            console.log("ЗДЕСЬ МЕНЯЕТСЯ ДАМКА");
        selectedChecker.isKing = true;
        }
        
   
}

function getAvailableSpaces(onlyJump) {
    if (!player && selectedChecker.isKing == false) {
        checkSpace(9, false, onlyJump);
        checkSpace(7, false, onlyJump);
        checkSpace(-7, true, onlyJump);
        checkSpace(-9, true, onlyJump);
    }
    if (player && selectedChecker.isKing == false) {
        checkSpace(-9, false, onlyJump);
        checkSpace(-7, false, onlyJump);
        checkSpace(7, true, onlyJump);
        checkSpace(9, true, onlyJump);
    }
    if (selectedChecker.isKing == true) {
        checkSpaceForKing(7, onlyJump);
        checkSpaceForKing(9, onlyJump);
        checkSpaceForKing(-7, onlyJump);
        checkSpaceForKing(-9, onlyJump);
    }
    if(selectedChecker.availableJumpSpaces.size!=0){
        selectedChecker.availableSpaces= new Set();
    }
}
function checkSpace(number, isBack, onlyJump) {
    var isJump = false;
    var numOfJump = 0;
    for (let i = selectedChecker.indexOfBoard + number; i <= board.length && i >= 0; i += number) {
        if (board[i] === null &&
            cells[i].classList.contains("white") == false &&
            isJump == false &&
            isBack == false &&
            onlyJump == false) {
            numOfJump += number;
            //if (i + numOfJump <= board.length)
                selectedChecker.availableSpaces.add(numOfJump);
            break;
        }
        else if (board[i] != null &&
            cells[i].classList.contains("white") == false &&
            board[i].id >= 12 && selectedChecker.id < 12 &&
            board[i + number] == null
        ) {
            numOfJump += number * 2;
            isJump = true;
            if (selectedChecker.indexOfBoard + numOfJump < board.length
                && selectedChecker.indexOfBoard + numOfJump >= 0
                && cells[selectedChecker.indexOfBoard + numOfJump].classList.contains("white") == false)
                selectedChecker.availableJumpSpaces.add(numOfJump);
            i = i + number;
            break;
        }
        else if (board[i] != null &&
            cells[i].classList.contains("white") == false &&
            board[i].id < 12 && selectedChecker.id >= 12 &&
            board[i + number] == null
        ) {
            numOfJump += number * 2;
            isJump = true;
            if (selectedChecker.indexOfBoard + numOfJump < board.length
                && selectedChecker.indexOfBoard + numOfJump >= 0
                && cells[selectedChecker.indexOfBoard + numOfJump].classList.contains("white") == false)
                selectedChecker.availableJumpSpaces.add(numOfJump);
            i = i + number;
            break;
        }
        else break;
    }
}

function checkSpaceForKing(number, onlyJump) {
    var isJump = false;
    var numOfJump = 0;
    for (let i = selectedChecker.indexOfBoard + number; i < board.length && i >= 0; i += number) {
        if (board[i] === null &&
            cells[i].classList.contains("white") == false// &&
           //isJump == false &&
           // onlyJump == false
            ) {
            numOfJump += number;
            if (selectedChecker.indexOfBoard + numOfJump < board.length
                && selectedChecker.indexOfBoard + numOfJump >= 0 && onlyJump==false)
                selectedChecker.availableSpaces.add(numOfJump);
        }
        else if (board[i] != null &&
            cells[i].classList.contains("white") == false &&
            board[i].id >= 12 && selectedChecker.id < 12 &&
            board[i + number] == null
        ) {
            numOfJump += number * 2;
            isJump = true;
            if (selectedChecker.indexOfBoard + numOfJump < board.length
                && selectedChecker.indexOfBoard + numOfJump >= 0
                && cells[selectedChecker.indexOfBoard + numOfJump].classList.contains("white") == false)
                {
                    selectedChecker.availableJumpSpaces.add(numOfJump);
                    let allNumOfJump=numOfJump;
                    for(let j=selectedChecker.indexOfBoard+allNumOfJump;j<board.length && j>=0;j+=number){
        
                        if(board[selectedChecker.indexOfBoard+allNumOfJump+number]==null && cells[selectedChecker.indexOfBoard+allNumOfJump+number]!=null &&  cells[selectedChecker.indexOfBoard+allNumOfJump+number].classList.contains("white") == false){
                            selectedChecker.availableJumpSpaces.add(allNumOfJump+number);
                              allNumOfJump+=number;
                        }
                        else break;
               
                    }
                }
            i = i + number;
            
           
        }
        else if (board[i] != null &&
            cells[i].classList.contains("white") == false &&
            board[i].id < 12 && selectedChecker.id >= 12 &&
            board[i + number] == null
        ) {
            numOfJump += number * 2;
            isJump = true;
            if (selectedChecker.indexOfBoard + numOfJump < board.length
                && selectedChecker.indexOfBoard + numOfJump >= 0
                && cells[selectedChecker.indexOfBoard + numOfJump].classList.contains("white") == false){

                   selectedChecker.availableJumpSpaces.add(numOfJump);
                    let allNumOfJump=numOfJump;
                    for(let j=selectedChecker.indexOfBoard+allNumOfJump;j<board.length && j>=0;j+=number){
                    
                        if(board[selectedChecker.indexOfBoard+allNumOfJump+number]==null && cells[selectedChecker.indexOfBoard+allNumOfJump+number]!=null && cells[selectedChecker.indexOfBoard+allNumOfJump+number].classList.contains("white") == false){
                            selectedChecker.availableJumpSpaces.add(allNumOfJump+number);
                             allNumOfJump+=number;
                        }
                        else break;
                    }
                }
            i = i + number;
            
          
        }
      
    }
    
}

function checkJumpSpaceForAll(){
    let checkersWithJump=[];
    let isWhite=false;
    let isBlack=false;
    for(let i=0;i<board.length;i++){
        if(board[i]!=null){
            selectedChecker=board[i];
            selectedChecker.availableJumpSpaces=new Set();
            selectedChecker.availableSpaces=new Set();
            getAvailableSpaces();
            if(selectedChecker.availableJumpSpaces.size!=0){
                checkersWithJump.push(selectedChecker);
                if(selectedChecker.id<12) isWhite=true;
                else isBlack=true;
            }
            
        }
    }
        if(checkersWithJump.length!=0 && isBlack){
        for(let i=0;i<board.length;i++){
        if(board[i]!=null && checkersWithJump.indexOf(board[i]) == -1 && player && board[i].id>=12){
            document.getElementById(board[i].id).removeEventListener("click", getPlayerCheckers);
            
        }
        }
       
    }
     if (checkersWithJump.length!=0 && isWhite) {
        for(let i=0;i<board.length;i++){
            if(board[i]!=null && checkersWithJump.indexOf(board[i]) == -1 && !player && board[i].id<12){
                document.getElementById(board[i].id).removeEventListener("click", getPlayerCheckers);
                
            }
            }
    }
    // for(let i=0;i<checkersWithJump.length;i++){
    //     console.log(checkersWithJump[i]);
    //    if(player && checkersWithJump[i].id>=12){
    //     for(const value of checkersWithJump[i].availableJumpSpaces){
    //           cells[checkersWithJump[i].indexOfBoard + value].style.background = redColor;
    //       }
    //    }
    //    if(!player && checkersWithJump[i].id<12){
    //     for(const value of checkersWithJump[i].availableJumpSpaces){
    //         cells[checkersWithJump[i].indexOfBoard + value].style.background = redColor;
    //     }
    //    }
      
   // }
    selectedChecker=null;
}
    
    


function checkCheckerConditions() {
    if (selectedChecker.isKing) {
        giveCellsClick();
    } else {
        if (player) {
            for (const value of selectedChecker.availableSpaces) {
                if (value > 0) {
                    selectedChecker.availableSpaces.delete(value);
                }
            }


        } else {
            for (const value of selectedChecker.availableSpaces) {
                if (value < 0) {
                    selectedChecker.availableSpaces.delete(value);
                }
            }

        }
        giveCellsClick();
    }
}


function giveCellsClick() {
    for (const value of selectedChecker.availableJumpSpaces) {
        giveClick(value, true)
    }
    
    for (const value of selectedChecker.availableSpaces) {
        giveClick(value, false)
    }

}

function giveClick(number, isJump) {
    if (isJump) {
        cells[selectedChecker.indexOfBoard + number].style.background = redColor;
    } else {
        cells[selectedChecker.indexOfBoard + number].style.background = greenColor;
    }
    cells[selectedChecker.indexOfBoard + number].setAttribute("onclick", "makeMove(" + number + ")");
}

function makeMove(number) {
    isMove = true;
    document.getElementById(selectedChecker.id).remove();
    cells[selectedChecker.indexOfBoard].innerHTML = "";
    if (player) {
        if (selectedChecker.isKing) {
            cells[selectedChecker.indexOfBoard + number].innerHTML = '<div class="blackChecker-king" id="' + selectedChecker.id + '"}>';
            blackChecker = [...document.getElementsByClassName("blackChecker"), ...document.getElementsByClassName("blackChecker-king")];
        } else {
            cells[selectedChecker.indexOfBoard + number].innerHTML = '<div class="blackChecker" id="' + selectedChecker.id + '"}>';
            blackChecker = [...document.getElementsByClassName("blackChecker"), ...document.getElementsByClassName("blackChecker-king")];
        }
    } else {
        if (selectedChecker.isKing) {
            cells[selectedChecker.indexOfBoard + number].innerHTML = '<div class="whiteChecker-king" id="' + selectedChecker.id + '"}>';
            whiteChecker = [...document.getElementsByClassName("whiteChecker"), ...document.getElementsByClassName("whiteChecker-king")];
        } else {
            cells[selectedChecker.indexOfBoard + number].innerHTML = '<div class="whiteChecker" id="' + selectedChecker.id + '"}>';
            whiteChecker = [...document.getElementsByClassName("whiteChecker"), ...document.getElementsByClassName("whiteChecker-king")];
        }
    }

    let index = selectedChecker.indexOfBoard
    if (number >= 14 || number <= -14) {
        if (number % 7 == 0 && number > 0)
            changeData(index, index + number, getRemoved(number, index, 7), 7);
        if (number % 7 == 0 && number < 0) {
            changeData(index, index + number, getRemoved(number, index, -7), -7);
        }
        if (number % 9 == 0 && number > 0) {
            changeData(index, index + number, getRemoved(number, index, 9), 9);
        }
        if (number % 9 == 0 && number < 0) {
            changeData(index, index + number, getRemoved(number, index, -9), -9);
        }
    } else
        changeData(index, index + number);
}
function getRemoved(number, index, side) {
    let removed = [];
    if (side > 0) {
        for (let i = index; i < index + number; i += side) {
        
            if (board[i] != null && board[index].id >= 12 && board[i].id < 12) {
                removed.push(i);
            }
            else if (board[i] != null && board[index].id < 12 && board[i].id >= 12) {
                removed.push(i);
            }
        }
    }
    else {
        for (let i = index; i > index + number; i += side) {
            if (board[i] != null && board[index].id >= 12 && board[i].id < 12) {
                removed.push(i);
            }
            else if (board[i] != null && board[index].id < 12 && board[i].id >= 12) {
                removed.push(i);
            }
        }
    }
    return removed;
}

function changeData(indexOfBoard, modifiedIndex, removePiece, side) {
    
    if (!removePiece) {
        partsBeforeEnd += cells[indexOfBoard].id + "-" + cells[modifiedIndex].id + " ";
    }
    else {
        partsBeforeEnd += cells[indexOfBoard].id;
            partsBeforeEnd += ":";
            partsBeforeEnd += cells[modifiedIndex].id;
            partsBeforeEnd+=" ";

    }
    board[indexOfBoard] = null;
    board[modifiedIndex] = new Checker(selectedChecker.id, modifiedIndex);
    if (!player && selectedChecker.id < 12 && modifiedIndex >= 57) {
        console.log("ПРОВЕРКА НА ДАМКУ");
        console.log(selectedChecker.id);
        console.log(modifiedIndex);
        document.getElementById(selectedChecker.id).classList.add("whiteChecker-king")
        selectedChecker.isKing=true;
    }
    if (player && selectedChecker.id >= 12 && modifiedIndex <= 7) {
        console.log("ПРОВЕРКА НА ДАМКУ");
        console.log(selectedChecker.id);
        console.log(modifiedIndex);
        document.getElementById(selectedChecker.id).classList.add("blackChecker-king");
        selectedChecker.isKing=true;
    }
    if (removePiece) {

        for (const value of removePiece) {
            board[value] = null;

            if (!player && selectedChecker.id < 12) {
                cells[value].innerHTML = "";
                blackScore--;

            }
            if (player && selectedChecker.id >= 12) {
                cells[value].innerHTML = "";
                whiteScore--;
            }
        }
    }
    getAvailableSpaces(true);
    removeCellsSelection();
    checkForWin();
    let index = selectedChecker.id;
    let indexOfBoardNow= selectedChecker.indexOfBoard;
    let isJump = selectedChecker.availableJumpSpaces;

    // selectedChecker.availableJumpSpaces=new Set();
    // getAvailableSpaces();
   
    
    cancelText.style.background = "#282623";
    cancelText.setAttribute("onclick", "cancel(" + index + "," + indexOfBoard + ")");
    removeCellonclick();
    resetSelectedChecker();
    removeEventListeners();
    if (isJump.size > 0) {
        document.getElementById(index).addEventListener('click', getPlayerCheckers);
    }
    selectedChecker=board[modifiedIndex];
    getAvailableSpaces(true);
    console.log("REMOVE");
    console.log(removePiece);
    if(board[modifiedIndex].availableJumpSpaces.size==0 || !removePiece || removePiece.length==0 ){
        endText.setAttribute("onclick", "end()");
        endText.style.background = "#282623";
    }
    else {endText.removeAttribute("onclick","end()");
    endText.removeAttribute("style");}
resetSelectedChecker();

}

function removeEventListeners() {
    if (player) {
        for (const value of blackChecker) {
            value.removeEventListener("click", getPlayerCheckers);
        }
    } else {
        for (const value of whiteChecker) {
            value.removeEventListener("click", getPlayerCheckers);
        }
    }

}


function checkForWin() {
    if (blackScore === 0) {
        blackTurntext.style.display = "none";
        winDisplay.style.display = "block";
        winText.innerHTML = "Белые выиграли"

    } else if (whiteScore === 0) {
        whiteTurnText.style.display = "none";
        winDisplay.style.display = "block";
        winText.innerHTML = "Красные выиграли"
    }

}

function changePlayer() {
    if (player) {
        player = 0;
        whiteTurnText.style.display = "block";
        blackTurntext.style.display = "none";

    } else {
        player = 1;
        whiteTurnText.style.display = "none";
        blackTurntext.style.display = "block";
    }
    setListener();
}


