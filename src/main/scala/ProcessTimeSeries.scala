import java.io.FileNotFoundException

import scala.collection.mutable
import scala.io.Source

object ProcessTimeSeries extends App{

  args.lift(0).fold{
    println("Please pass file name as command line argument.")
  }{fileName =>

    val standardDelimiter = "\t"
    val timeSeriesStack = new mutable.Stack[TimeSeries]

    println(s"T${standardDelimiter}${standardDelimiter}V${standardDelimiter}N${standardDelimiter}RS${standardDelimiter}MinV${standardDelimiter}MaxV")
    println("-------------------------------------------------------")

    try{
      for(line <- Source.fromFile(fileName).getLines){
        val record = line.split(standardDelimiter)
        val time = record(0).toLong
        val value = record(1).toFloat

        var observations = 1
        var rollingSum = value
        var minValue = value
        var maxValue = value

        val tempTimeSeriesStack = new mutable.Stack[TimeSeries]
        var isTimeInLimit = true

        while(timeSeriesStack.headOption.isDefined && isTimeInLimit){
          val timeSeries = timeSeriesStack.pop
          if(timeSeries.time >= time - 60){
            observations = observations + 1
            rollingSum = BigDecimal(rollingSum + timeSeries.value).setScale(5, BigDecimal.RoundingMode.HALF_UP).toFloat
            if(timeSeries.value < minValue){
              minValue = timeSeries.value
            }
            if(timeSeries.value > maxValue){
              maxValue = timeSeries.value
            }
          }else{
            isTimeInLimit = false
          }
          tempTimeSeriesStack.push(timeSeries)
        }

        timeSeriesStack.pushAll(tempTimeSeriesStack)
        timeSeriesStack.push(TimeSeries(time, value, observations, rollingSum, minValue, maxValue))

        println(s"$time$standardDelimiter$value$standardDelimiter$observations$standardDelimiter$rollingSum$standardDelimiter$minValue$standardDelimiter$maxValue")
      }
    }catch{
      case ioe: FileNotFoundException => println(s"Kindly pass the valid file name as command line argument. Filename '$fileName' not found.")
      case ex: Exception => println(s"Exception occurred while executing application: $ex - ${ex.getMessage}")
    }
  }
}

case class TimeSeries(time: Long, value: Float, observations: Int, rollingSum: Float, minValue: Float, maxValue: Float)