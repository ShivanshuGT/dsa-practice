public class DesignBrowserHistory {

    static class Node{
        String data;
        Node next;
        Node prev;

        Node(String data){
            this.data = data;
        }
    }

    static class Browser{
        Node current;

        Browser(String homePage){
            current = new Node(homePage);
            // TC -> O(1)
            // SC -> O(1)
        }

        public void visit(String url){
            Node newNode = new Node(url);
            current.next = newNode;
            newNode.prev = current;
            current = current.next;
            // TC -> O(1)
            // SC -> O(1)
        }

        public String forward(int steps){
            while (steps > 0) {
                if(current.next != null){
                    current = current.next;
                }else{
                    break;
                }
                steps -= 1;
            }
            return current.data;
            // TC -> O(steps)
            // SC -> O(1)
        }

        public String back(int steps){
            while (steps > 0) {
                if(current.prev != null){
                    current = current.prev;
                }else{
                    break;
                }
                steps -= 1;
            }
            return current.data;
            // TC -> O(steps)
            // SC -> O(1)
        }

    }

    public static void main(String[] args) {

        Browser browser = new Browser("tuf.org");
        browser.visit("google.com");
        browser.visit("instagram.com");
        browser.visit("fb.com");
        System.out.println(browser.back(1));
        System.out.println(browser.back(1));
        System.out.println(browser.forward(1));
        browser.visit("tuf.org");
        System.out.println(browser.forward(2));
        System.out.println(browser.back(2));
        System.out.println(browser.back(7));
        
    }
}
