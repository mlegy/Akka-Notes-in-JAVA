package controllers;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import play.*;
import play.mvc.*;

import views.html.*;

public class Application extends Controller {

    public Result index() throws InterruptedException {

        //Initialize the ActorSystem
        final ActorSystem actorSystem = ActorSystem.create("UniversityMessageSystem");

        //construct the Teacher Actor Ref
        final ActorRef teacherActorRef = actorSystem.actorOf(Props.create(TeacherActor), "teacherActorRef");

        //send a message to the Teacher Actor
        teacherActorRef.tell(QuoteRequest);

        //Let's wait for a couple of seconds before we shut down the system
        Thread.sleep(2000);

        //Shut down the ActorSystem.
        actorSystem.shutdown();

        return ok();
    }

}