package com.gmail.norinori6791.personalstats;

import java.text.NumberFormat;

public class BasketBall {
	private String in_date;
	private int game;
	private int successGoal2;
	private int attemptGoal2;
	private int successGoal3;
	private int attemptGoal3;
	private int successFreeThrow;
	private int attemptFreeThrow;
	private int asist;
	private int orebound;
	private int drebound;
	private int steal;
	private int block;
	private int turnover;

	public int getPointAverage(){
		if(this.successFreeThrow == 0 && this.successGoal2 == 0 && this.successGoal3 == 0){
			return 0;
		}
		return (int)(this.successFreeThrow+(this.successGoal2*2)+(this.successGoal3*3))/this.game;	
	}
	public int getPoint(){
		return this.successFreeThrow+(this.successGoal2*2)+(this.successGoal3*3);	
	}
	public String getFieldGoal(){
		if( this.successGoal2 == 0 && this.successGoal3 == 0){
			return "0%";
		}else{
			NumberFormat nf = NumberFormat.getPercentInstance();
			return nf.format((double)(this.successGoal2+this.successGoal3)/(double)(this.attemptGoal2+this.attemptGoal3));	
		}
	}
	public String GetFieldGoal2(){
		if( this.successGoal2 == 0 ){
			return "0%";
		}else{
			NumberFormat nf = NumberFormat.getPercentInstance();
			return nf.format((double)this.successGoal2/(double)(this.attemptGoal2));
		}
	}
	public String GetFieldGoal3(){
		if( this.successGoal3 == 0 ){
			return "0%";
		}else{
			NumberFormat nf = NumberFormat.getPercentInstance();
			return nf.format((double)this.successGoal3/(double)(this.attemptGoal3));
		}
	}
	public String GetFTP(){
		if( this.successFreeThrow == 0 ){
			return "0%";
		}else{
			NumberFormat nf = NumberFormat.getPercentInstance();
			return nf.format((double)this.successFreeThrow/(double)(this.attemptFreeThrow));	
		}
	}
	public void setDate(String in_d){
		this.in_date = in_d;
	}
	public String getDate(){
		return this.in_date;
	}
	public String getDateFormat(){
		return String.format("%s年%s月%s日%s時%s分%s秒", 
							this.in_date.substring(0,4),
							this.in_date.substring(4,6),
							this.in_date.substring(6,8),
							this.in_date.substring(8,10),
							this.in_date.substring(10,12),
							this.in_date.substring(12,14)
							);
	}
	public void setGame(int count){
		this.game = count;
	}
	public int getGame(){
		return this.game;
	}
	public void setSuccessGoal2(int success){
		this.successGoal2 = success;
	}
	public void setAttemptGoal2(int attempt){
		this.attemptGoal2 = attempt;
	}
	public void setSuccessGoal2(){
		this.successGoal2 = this.successGoal2 + 1;
	}
	public void setAttemptGoal2(){
		this.attemptGoal2 = this.attemptGoal2 + 1;
	}
	public void setSuccessGoal3(int success){
		this.successGoal3 = success;
	}
	public void setAttemptGoal3(int attempt){
		this.attemptGoal3 = attempt;
	}
	public void setSuccessGoal3(){
		this.successGoal3 = this.successGoal3 + 1;
	}
	public void setAttemptGoal3(){
		this.attemptGoal3 = this.attemptGoal3 + 1;
	}
	public void setSuccessFT(int success){
		this.successFreeThrow= success;
	}
	public void setAttemptFT(int attempt){
		this.attemptFreeThrow = attempt;
	}
	public void setSuccessFT(){
		this.successFreeThrow = this.successFreeThrow + 1;
	}
	public void setAttemptFT(){
		this.attemptFreeThrow = this.attemptFreeThrow + 1;
	}
	public int getAttemptGoal2(){
		return this.attemptGoal2;
	}
	public int getAverageAttemptGoal2(){
		if(this.attemptGoal2 == 0){
			return 0;
		}
		return this.attemptGoal2 / this.game;
	}
	public int getAttemptGoal3(){
		return this.attemptGoal3;
	}
	public int getAverageAttemptGoal3(){
		if(this.attemptGoal3 == 0){
			return 0;
		}
		return (int)this.attemptGoal3 / this.game;
	}
	public int getGoal2(){
		return this.successGoal2;
	}
	public int getAverageGoal2(){
		if(this.successGoal2 == 0){
			return 0;
		}
		return this.successGoal2/this.game;
	}
	public int getGoal3() {
		return this.successGoal3;
	}
	public int getAverageGoal3(){
		if(this.successGoal3 == 0){
			return 0;
		}
		return this.successGoal3/this.game;
	}
	public int getFT() {
		return this.successFreeThrow;
	}
	public int getAverageFT(){
		if(this.successFreeThrow == 0){
			return 0;
		}
		return this.successFreeThrow/this.game;
	}
	public int getAttemptFT(){
		return this.attemptFreeThrow;
	}
	public int getAsist() {
		return asist;
	}
	public int getAverageAsist(){
		if(this.asist == 0){
			return 0;
		}
		return this.asist/this.game;
	}
	public void setAsist(int asist) {
		this.asist = asist;
	}
	public void setAsist(){
		this.asist = this.asist + 1;
	}
	public int getOrebound() {
		return orebound;
	}
	public int getAverageOrebound(){
		if(this.orebound == 0){
			return 0;
		}
		return this.orebound/this.game;
	}
	public void setOrebound(int orebound) {
		this.orebound = orebound;
	}
	public void setOrebound(){
		this.orebound = this.orebound + 1;
	}
	public int getDrebound() {
		return drebound;
	}
	public int getAverageDrebound(){
		if(this.drebound == 0){
			return 0;
		}
		return this.drebound/this.game;
	}
	public void setDrebound(int drebound) {
		this.drebound = drebound;
	}
	public void setDrebound(){
		this.drebound = this.drebound + 1;
	}
	public int getSteal() {
		return steal;
	}
	public int getAverageSteal(){
		if(this.steal == 0){
			return 0;
		}
		return this.steal/this.game;
	}
	public void setSteal(int steal) {
		this.steal = steal;
	}
	public void setSteal(){
		this.steal = this.steal + 1;
	}
	public int getBlock() {
		return block;
	}
	public int getAverageBlock(){
		if(this.block == 0){
			return 0;
		}
		return this.block/this.game;
	}
	public void setBlock(){
		this.block = this.block + 1;
	}
	public void setBlock(int block) {
		this.block = block;
	}
	public int getTurnover() {
		return turnover;
	}
	public int getAverageTurnover(){
		if(this.turnover == 0){
			return 0;
		}
		return this.turnover/this.game;
	}
	public void setTurnover(int turnover) {
		this.turnover = turnover;
	}
	public void setTurnover(){
		this.turnover = this.turnover + 1;
	}
	public void clear(){
		this.successGoal2 = 0;
		this.attemptGoal2 = 0;
		this.successGoal3 = 0;
		this.attemptGoal3 = 0;
		this.successFreeThrow = 0;
		this.attemptFreeThrow = 0;
		this.asist = 0;
		this.orebound = 0;
		this.drebound = 0;
		this.steal = 0;
		this.block = 0;
		this.turnover = 0;
	}
	
}
