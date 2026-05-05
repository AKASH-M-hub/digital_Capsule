/// genearte code for the media.java
/// media -d,capsule id,file url,file type
/// generate code for media entity

package com.example.timecapsule.entity;
import jakarta.persistence.*;
import lombok.*;
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Media {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mediaId;

    private String fileUrl;

    private String fileType;

    @ManyToOne
    @JoinColumn(name = "capsule_id")
    private Capsule capsule;
}