module sample {
	exports application;
	exports controller;
	
	opens dao;
	opens controller;
	opens model;
	
	requires javafx.base;
	requires javafx.fxml;
	requires javafx.graphics;
	requires java.sql;
	requires javafx.controls;
	requires javafx.media;
}