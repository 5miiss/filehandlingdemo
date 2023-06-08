package com.example.filehandlingdemo.notifications.entities;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Notification
{
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private long id;
	
	private boolean statRead ;
	private String msg;
	@Transient
	private Map<String,Object> payload;
	private String title;
    @CreatedDate
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
	@Column(updatable = false)
	private LocalDateTime createdAt;

//    public NotificationType NotificationType { get; set; }
//
//    @ManyToOne(fetch = FetchType.EAGER)
//    private Client client;
//
//
//	@ManyToOne(fetch = FetchType.EAGER)
//	@Nullable
//	private Facility facility;

	@Transient
	private List<String> userIds;
}