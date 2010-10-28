import org.squeryl.PrimitiveTypeMode._
import org.squeryl.Schema
import org.squeryl.KeyedEntity
import scala.collection.immutable.SortedSet
import org.squeryl.dsl.CompositeKey2
import org.squeryl.SessionFactory
import org.squeryl.Session
import org.squeryl.adapters.MySQLAdapter
import java.sql.DriverManager

case class Sample(kind:Long, date:Long) 
    extends KeyedEntity[CompositeKey2[Long,Long]] {
  def this() = this(0,0)
  override val id = compositeKey(kind, date)
}

object Samples extends Schema {
  val samples = table[Sample]
}

object Main {
  def connect(dbHost:String, user:String, password:String) {
    Class.forName("com.mysql.jdbc.Driver")
    val connectString =
      "jdbc:mysql://%s/test1?user=%s&password=%s" format(dbHost, user, password)

    SessionFactory.concreteFactory = Some( ()=> {
      Session.create(DriverManager.getConnection(connectString), new MySQLAdapter)
    })
  }

  def main(args:Array[String]) {
    connect("localhost", "test", "test")

    inTransaction {  
      Samples.printDdl 
    }
  }
}
