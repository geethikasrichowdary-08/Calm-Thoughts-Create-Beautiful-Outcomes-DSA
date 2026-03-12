import java.util.*;

/* USER CLASS */
class User {
    String username;
    String email;
    String password;

    User(String u, String e, String p){
        username = u;
        email = e;
        password = p;
    }
}

/* LINKED LIST FOR SAVED FEELINGS */
class FeelingNode{
    String message;
    FeelingNode next;

    FeelingNode(String msg){
        message = msg;
        next = null;
    }
}

class FeelingList{
    FeelingNode head;

    void saveFeeling(String msg){
        FeelingNode newNode = new FeelingNode(msg);

        if(head == null){
            head = newNode;
        }else{
            FeelingNode temp = head;
            while(temp.next != null){
                temp = temp.next;
            }
            temp.next = newNode;
        }
    }

    void showFeelings(){
        FeelingNode temp = head;

        while(temp != null){
            System.out.println("Saved Feeling: " + temp.message);
            temp = temp.next;
        }
    }
}

/* STACK FOR USER ACTION HISTORY */
class ActionStack{
    Stack<String> stack = new Stack<>();

    void addAction(String action){
        stack.push(action);
    }

    void showHistory(){
        System.out.println("Recent Actions:");
        while(!stack.isEmpty()){
            System.out.println(stack.pop());
        }
    }
}

/* QUEUE FOR ARTICLE REQUESTS */
class ArticleQueue{
    Queue<String> queue = new LinkedList<>();

    void addRequest(String article){
        queue.add(article);
    }

    void processRequest(){
        if(!queue.isEmpty()){
            System.out.println("Opening Article: " + queue.poll());
        }
    }
}

/* SEARCHING + SORTING ARTICLES */
class ArticleManager{

    String articles[] = {
            "Peace begins with calm",
            "Grow slowly",
            "Positive thoughts create outcomes",
            "Respond gently",
            "Tomorrow is unwritten"
    };

    /* BUBBLE SORT */
    void sortArticles(){

        for(int i=0;i<articles.length-1;i++){
            for(int j=0;j<articles.length-i-1;j++){

                if(articles[j].compareTo(articles[j+1]) > 0){
                    String temp = articles[j];
                    articles[j] = articles[j+1];
                    articles[j+1] = temp;
                }
            }
        }

        System.out.println("\nSorted Articles:");
        for(String a : articles)
            System.out.println(a);
    }

    /* LINEAR SEARCH */
    void searchArticle(String key){

        for(int i=0;i<articles.length;i++){
            if(articles[i].equalsIgnoreCase(key)){
                System.out.println("Article Found: "+articles[i]);
                return;
            }
        }

        System.out.println("Article Not Found");
    }
}

/* HASHMAP FOR USER DATABASE */
class UserDatabase{

    HashMap<String,User> users = new HashMap<>();

    void register(User u){
        users.put(u.username,u);
    }

    boolean login(String username,String password){

        if(users.containsKey(username)){
            if(users.get(username).password.equals(password)){
                return true;
            }
        }
        return false;
    }
}

/* MAIN PROGRAM */

public class CalmThoughts {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        UserDatabase db = new UserDatabase();
        FeelingList feelings = new FeelingList();
        ActionStack history = new ActionStack();
        ArticleQueue requests = new ArticleQueue();
        ArticleManager manager = new ArticleManager();

        /* CREATE ACCOUNT */
        System.out.println("=== Create Account ===");

        System.out.print("Enter Username: ");
        String username = sc.nextLine();

        System.out.print("Enter Email: ");
        String email = sc.nextLine();

        System.out.print("Enter Password: ");
        String password = sc.nextLine();

        db.register(new User(username,email,password));

        System.out.println("Account Created Successfully\n");

        /* LOGIN */

        System.out.println("=== Login ===");

        System.out.print("Enter Username: ");
        String loginUser = sc.nextLine();

        System.out.print("Enter Password: ");
        String loginPass = sc.nextLine();

        if(db.login(loginUser,loginPass)){
            System.out.println("\nLogin Successful\n");
        }else{
            System.out.println("\nLogin Failed");
            return;
        }

        /* SAVE FEELINGS */

        feelings.saveFeeling("Today I feel calm");
        feelings.saveFeeling("I handled stress peacefully");

        feelings.showFeelings();

        /* STACK HISTORY */

        history.addAction("Opened Articles");
        history.addAction("Saved Feeling");

        history.showHistory();

        /* QUEUE REQUEST */

        requests.addRequest("Peace begins with calm");
        requests.addRequest("Grow slowly");

        requests.processRequest();

        /* SORT ARTICLES */

        manager.sortArticles();

        /* SEARCH ARTICLE */

        manager.searchArticle("Grow slowly");

    }
}