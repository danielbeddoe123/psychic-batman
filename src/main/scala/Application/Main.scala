package com.monitise.beddoed.Application

import com.monitise.beddoed.Validator.CLIValidator

import twitter4j._
import twitter4j.conf.ConfigurationBuilder

object Main extends App {

    CLIValidator.validateCLIArgs(args)

    val terms: Set[String] = args.toSet

    val twitterStream = new TwitterStreamFactory(Util.config).getInstance
    twitterStream.addListener(Util.simpleStatusListener)
    twitterStream.filter(new FilterQuery().track(args))
    Thread.sleep(30000)
    twitterStream.cleanUp
    twitterStream.shutdown

}


object Util {
    val config = new ConfigurationBuilder()
      .setOAuthConsumerKey("IhhyyoE8m1n0ltO0k8DbO4Elg")
      .setOAuthConsumerSecret("tR4gHIlyoyk43jbjIIcwKlSPYDucskNs5Uw8c7NE3pIF5YOdZn")
      .setOAuthAccessToken("359057307-rSUKga2xxptI2G2NJIxZpv9WIAw2Jl3h0eWaXKWG")
      .setOAuthAccessTokenSecret("2LnsRH35XmehyWnF04FCtbt7v1mNUxiIIfq4Xny5SVkha")
      .build

    def simpleStatusListener = new StatusListener() {
      def onStatus(status: Status) { println(status.getText) }
      def onDeletionNotice(statusDeletionNotice: StatusDeletionNotice) {}
      def onTrackLimitationNotice(numberOfLimitedStatuses: Int) {}
      def onException(ex: Exception) { ex.printStackTrace }
      def onScrubGeo(arg0: Long, arg1: Long) {}
      def onStallWarning(warning: StallWarning) {}
    }
}