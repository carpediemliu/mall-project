package cn.e3mall.activemq;

import static org.junit.Assert.*;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQTextMessage;
import org.junit.Test;

public class TestActiveMQ {

	/**
	 * 点到点形式发送消息，一个生产者，一个消费者，被消费完之后就会被删除
	 */
	@Test
	public void testQueueProducer() throws Exception {
		// 1.创建连接工厂对象，需要指定IP与端口号,一个Activemq即一个broker
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://192.168.25.128:61616");
		// 2.使用工厂对象创建一个connection对象
		Connection connection = connectionFactory.createConnection();
		// 3.建立连接，调用connection的start方法
		connection.start();
		// 4.创建一个session对象
		// session第一个参数：表示是否开启MQ的事务（发送失败重新发送）
		// 第二个参数：在关闭事务下才有意义：为应答模式：手动和自动，一般为自动应答
		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		// 5.使用session对象创建一个destination对象（两种形式：Queue or Topic）
		Queue queue = session.createQueue("test-queue");
		// 6.使用session创建一个producer对象（生产者）
		MessageProducer producer = session.createProducer(queue);
		// 7.创建一个Message消息对象
		/*
		 * TextMessage textMessage = new ActiveMQTextMessage();
		 * textMessage.setText("hello");
		 */
		TextMessage textMessage = session.createTextMessage("hello activemq");
		// 8.发送消息
		producer.send(textMessage);
		// 9.关闭资源
		producer.close();
		session.close();
		connection.close();
	}

	/**
	 * 接收消息
	 */
	@Test
	public void testQueueConsumer() throws Exception {
		// 创建连接工厂，连接MQ
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://192.168.25.128:61616");
		// 创建连接对象
		Connection connection = connectionFactory.createConnection();
		// 开启连接
		connection.start();
		// 创建session
		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		// 创建destination
		Queue queue = session.createQueue("test-queue");
		// 创建消费者
		MessageConsumer consumer = session.createConsumer(queue);
		// 接收消息,监听队列
		consumer.setMessageListener(new MessageListener() {
			public void onMessage(Message message) {
				// 打印结果
				TextMessage textMessage = (TextMessage) message;
				try {
					String text = textMessage.getText();
					System.out.println(text);
				} catch (JMSException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		// 等待接收消息
		// System.in.read();
		// 关闭资源
		consumer.close();
		session.close();
		connection.close();
	}

	@Test
	public void testTopicProducer() throws Exception {
		// 1.创建连接工厂对象，需要指定IP与端口号,一个Activemq即一个broker
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://192.168.25.128:61616");
		// 2.使用工厂对象创建一个connection对象
		Connection connection = connectionFactory.createConnection();
		// 3.建立连接，调用connection的start方法
		connection.start();
		// 4.创建一个session对象
		// session第一个参数：表示是否开启MQ的事务（发送失败重新发送）
		// 第二个参数：在关闭事务下才有意义：为应答模式：手动和自动，一般为自动应答
		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		// 5.使用session对象创建一个destination对象（两种形式：Queue or Topic）
		Topic topic = session.createTopic("test-topic");
		// 6.使用session创建一个producer对象（生产者）
		MessageProducer producer = session.createProducer(topic);
		// 7.创建一个Message消息对象
		/*
		 * TextMessage textMessage = new ActiveMQTextMessage();
		 * textMessage.setText("hello");
		 */
		TextMessage textMessage = session.createTextMessage("topic message");
		// 8.发送消息
		producer.send(textMessage);
		// 9.关闭资源
		producer.close();
		session.close();
		connection.close();
	}

	@Test
	public void testTopicConsumer() throws Exception {
		// 创建连接工厂，连接MQ
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://192.168.25.128:61616");
		// 创建连接对象
		Connection connection = connectionFactory.createConnection();
		// 开启连接
		connection.start();
		// 创建session
		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		// 创建destination
		Topic topic = session.createTopic("test-topic");
		// 创建消费者
		MessageConsumer consumer = session.createConsumer(topic);
		// 接收消息,监听队列
		consumer.setMessageListener(new MessageListener() {
			public void onMessage(Message message) {
				// 打印结果
				TextMessage textMessage = (TextMessage) message;
				try {
					String text = textMessage.getText();
					System.out.println(text);
				} catch (JMSException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		// 等待接收消息
		// System.in.read();
		// 关闭资源
		consumer.close();
		session.close();
		connection.close();

	}
}
