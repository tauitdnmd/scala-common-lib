package education.x.commons

import education.x.ultis.Implicits._
import org.nutz.ssdb4j.spi.SSDB

import scala.concurrent.{ExecutionContext, Future}

trait List[T] {

  def pushFront(value: T): Future[Boolean]

  def pushBack(value: T): Future[Boolean]

  def size(): Future[Option[Int]]

  def popFront(): Future[Option[T]]

  def popBack(): Future[Option[T]]

  def clear(): Future[Boolean]

  def get(index: Int): Future[Option[T]]

  def getFront(): Future[Option[T]]

  def getBack(): Future[Option[T]]

  def get(from: Int, num: Int): Future[Option[Array[T]]]

  def set(index: Int, value: T): Future[Boolean]

  def getAll(): Future[Option[Array[T]]]
}

case class List32Impl(dbname: String, client: SSDB)(implicit ec: ExecutionContext = ExecutionContext.global) extends List[Int] {
  override def pushFront(value: Int): Future[Boolean] = Future {
    client.qpush_front(dbname, value).ok()
  }

  override def pushBack(value: Int): Future[Boolean] = Future {
    client.qpush_back(dbname, value).ok()
  }

  override def size(): Future[Option[Int]] = Future {
    val r = client.qsize(dbname)
    r.getIntAsOption()
  }

  override def popFront(): Future[Option[Int]] = Future {
    val r = client.qpop_front(dbname)
    r.getIntAsOption()
  }

  override def popBack(): Future[Option[Int]] = Future {
    val r = client.qpop_back(dbname)
    r.getIntAsOption()
  }

  override def clear(): Future[Boolean] = Future {
    client.qclear(dbname).ok()
  }

  override def get(index: Int): Future[Option[Int]] = Future {
    val r = client.qget(dbname, index)
    r.getIntAsOption()
  }

  override def get(from: Int, num: Int): Future[Option[Array[Int]]] = Future {
    val r = client.qrange(dbname, from, num)
    r.getArrayIntAsOption()
  }

  override def set(index: Int, value: Int): Future[Boolean] = Future {
    client.qset(dbname, index, value).ok()
  }


  override def getFront(): Future[Option[Int]] = Future {
    val r = client.qfront(dbname)
    r.getIntAsOption()
  }

  override def getBack(): Future[Option[Int]] = Future {
    val r = client.qback(dbname)
    r.getIntAsOption()
  }

  override def getAll(): Future[Option[Array[Int]]] = Future {
    val r = client.qsize(dbname)
    if (r.ok())
      client.qrange(dbname, 0, r.asInt()).getArrayIntAsOption()
    else
      None
  }
}


case class ListStringImpl(dbname: String, client: SSDB)(implicit ec: ExecutionContext = ExecutionContext.global) extends List[String] {
  override def pushFront(value: String): Future[Boolean] = Future {
    client.qpush_front(dbname, value).ok()
  }

  override def pushBack(value: String): Future[Boolean] = Future {
    client.qpush_back(dbname, value).ok()
  }

  override def size(): Future[Option[Int]] = Future {
    val r = client.qsize(dbname)
    r.getIntAsOption()
  }

  override def popFront(): Future[Option[String]] = Future {
    val r = client.qpop_front(dbname)
    r.getStringAsOption()
  }

  override def popBack(): Future[Option[String]] = Future {
    val r = client.qpop_back(dbname)
    r.getStringAsOption()
  }

  override def clear(): Future[Boolean] = Future {
    client.qclear(dbname).ok()
  }

  override def get(index: Int): Future[Option[String]] = Future {
    val r = client.qget(dbname, index)
    r.getStringAsOption()
  }

  override def get(from: Int, num: Int): Future[Option[Array[String]]] = Future {
    val r = client.qrange(dbname, from, num)
    r.getArrayStringAsOption()
  }

  override def set(index: Int, value: String): Future[Boolean] = Future {
    client.qset(dbname, index, value).ok()
  }

  override def getFront(): Future[Option[String]] = Future {
    val r = client.qfront(dbname)
    r.getStringAsOption()
  }

  override def getBack(): Future[Option[String]] = Future {
    val r = client.qback(dbname)
    r.getStringAsOption()
  }

  override def getAll(): Future[Option[Array[String]]] = Future {
    val r = client.qsize(dbname)
    if (r.ok())
      client.qrange(dbname, 0, r.asInt()).getArrayStringAsOption()
    else
      None
  }
}

