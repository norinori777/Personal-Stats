package com.gmail.norinori6791.personalstats;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

@RunWith(Enclosed.class)
public class BasketBallTest {

	public static class ゼロ以外を取得できる {
		
		BasketBall bb;
		
		@Before
		public void setup() throws Exception{
			bb = new BasketBall();
			bb.setAttemptFT(7);
			bb.setSuccessFT(5);
			bb.setAttemptGoal2(3);
			bb.setSuccessGoal2(3);
			bb.setAttemptGoal3(6);
			bb.setSuccessGoal3(3);
			
			bb.setAsist(5);
			bb.setBlock(5);
			bb.setDrebound(5);
			bb.setOrebound(5);
			bb.setSteal(5);
			bb.setTurnover(5);
		}
		
		@Test
		public void 総得点を取得できる() {
			assertThat(bb.getPoint(), is(20));
		}
		@Test
		public void フィールドゴールパーセンテージを取得できる(){
			assertThat(bb.getFieldGoal(), is("67%"));
		}
		@Test
		public void フィールドゴールパーセンテージ2PTを取得できる(){
			assertThat(bb.GetFieldGoal2(), is("100%"));
		}
		@Test
		public void フィールドゴールパーセンテージ3PTを取得できる(){
			assertThat(bb.GetFieldGoal3(), is("50%"));
		}
		@Test
		public void フリースローパーセンテージを取得できる(){
			assertThat(bb.GetFTP(), is("71%"));
		}
		@Test
		public void アシストを取得できる(){
			assertThat(bb.getAsist(), is(5));
		}
		@Test
		public void ブロックを取得できる(){
			assertThat(bb.getBlock(), is(5));
		}
		@Test
		public void オフェンスリバンドを取得できる(){
			assertThat(bb.getOrebound(), is(5));
		}
		@Test
		public void ディフェンスリバンドを取得できる(){
			assertThat(bb.getDrebound(), is(5));
		}
		@Test
		public void スティールを取得できる(){
			assertThat(bb.getSteal(), is(5));
		}
		@Test
		public void タンノーバーを取得できる(){
			assertThat(bb.getTurnover(), is(5));
		}
	}
	
	public static class ゼロを取得{

		BasketBall bb;
		
		@Before
		public void setup() throws Exception{
			bb = new BasketBall();
			bb.setAttemptFT(0);
			bb.setSuccessFT(0);
			bb.setAttemptGoal2(0);
			bb.setSuccessGoal2(0);
			bb.setAttemptGoal3(0);
			bb.setSuccessGoal3(0);
			
			bb.setAsist(0);
			bb.setBlock(0);
			bb.setDrebound(0);
			bb.setOrebound(0);
			bb.setSteal(0);
			bb.setTurnover(0);
		}
		public void 総得点を取得できる() {
			assertThat(bb.getPoint(), is(0));
		}
		@Test
		public void フィールドゴールパーセンテージを取得できる(){
			assertThat(bb.getFieldGoal(), is("0%"));
		}
		@Test
		public void フィールドゴールパーセンテージ2PTを取得できる(){
			assertThat(bb.GetFieldGoal2(), is("0%"));
		}
		@Test
		public void フィールドゴールパーセンテージ3PTを取得できる(){
			assertThat(bb.GetFieldGoal3(), is("0%"));
		}
		@Test
		public void フリースローパーセンテージを取得できる(){
			assertThat(bb.GetFTP(), is("0%"));
		}
		@Test
		public void アシストを取得できる(){
			assertThat(bb.getAsist(), is(0));
		}
		@Test
		public void ブロックを取得できる(){
			assertThat(bb.getBlock(), is(0));
		}
		@Test
		public void オフェンスリバンドを取得できる(){
			assertThat(bb.getOrebound(), is(0));
		}
		@Test
		public void ディフェンスリバンドを取得できる(){
			assertThat(bb.getDrebound(), is(0));
		}
		@Test
		public void スティールを取得できる(){
			assertThat(bb.getSteal(), is(0));
		}
		@Test
		public void タンノーバーを取得できる(){
			assertThat(bb.getTurnover(), is(0));
		}
	}
	public static class アベレージを取得{
		BasketBall bb;
		
		@Before
		public void setup() throws Exception{
			bb = new BasketBall();
			bb.setAttemptFT(15);
			bb.setSuccessFT(15);
			bb.setAttemptGoal2(15);
			bb.setSuccessGoal2(15);
			bb.setAttemptGoal3(15);
			bb.setSuccessGoal3(15);
			
			bb.setAsist(15);
			bb.setBlock(15);
			bb.setDrebound(15);
			bb.setOrebound(15);
			bb.setSteal(15);
			bb.setTurnover(15);
			
			bb.setGame(2);
		}
		@Test
		public void アベレージフリスロー試投数取得できる(){
			assertThat(bb.getAverageFT(), is(7));
		}
		@Test
		public void アベレージフリースロー成功数取得できる(){
			assertThat(bb.getAverageFT(), is(7));
		}
		@Test
		public void アベレージ２ＰＴ取得(){
			assertThat(bb.getAverageGoal2(), is(7));
		}
		@Test
		public void アベレージ３ＰＴ取得(){
			assertThat(bb.getAverageGoal3(), is(7));
		}
		@Test
		public void 試投アベレージ２ＰＴ取得(){
			assertThat(bb.getAverageAttemptGoal2(),is(7));
		}
		@Test
		public void 試投アベレージ３ＰＴ取得(){
			assertThat(bb.getAverageAttemptGoal3(),is(7));
		}
		@Test
		public void アベレージアシスト取得(){
			assertThat(bb.getAverageAsist(), is(7));
		}
		@Test
		public void アベレージオフェンスリバンド取得(){
			assertThat(bb.getAverageOrebound(), is(7));
		}
		@Test
		public void アベレージディフェンスリバンド取得(){
			assertThat(bb.getAverageDrebound(), is(7));
		}
		@Test
		public void アベレージスティール取得(){
			assertThat(bb.getAverageSteal(), is(7));
		}
		@Test
		public void アベレージブロック取得(){
			assertThat(bb.getAverageBlock(), is(7));
		}
		@Test
		public void アベレージタンノーバー取得(){
			assertThat(bb.getAverageTurnover(), is(7));
		}
	}
}
