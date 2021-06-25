package hello.core.singleton;
   public class SingletonService {
   private static final SingletonService instance = new SingletonService();
   public static SingletonService getInstance(){
               return instance;
            }

            private SingletonService(){ //외부에서 호출할 수 없게 된다. 객체 생성 x.
            }

            public void logic(){
                System.out.println("싱클톤 객체 로직 호출");
            }
}