package org.uaf.CD_WEB_2025.entity;



import jakarta.persistence.*;
        import lombok.*;
        import org.uaf.CD_WEB_2025.services.ProductServiceImp;

import java.io.Serializable;


@Entity
@Table(name = "image")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Image implements Serializable {

    @ManyToOne()
    @JoinColumn(name = "ID_PR", referencedColumnName = "ID_PR")
    private org.uaf.CD_WEB_2025.entity.Product product;
    @Id
    @Column(name = "ID_IMG")
    private String idImg;
    @Column(name = "URL")
    private String url;
    @Column(name = "STATUS")
    private int status;


    public Image(org.uaf.CD_WEB_2025.entity.Product product, String idImg, String fileUrl, int status) {
        this.product= product;
        this.idImg = idImg;
        this.url = fileUrl;
        this.status = status;
    }

    public String getAvt() {
        if (status == 0) {
            return url;
        }
        return null;
    }

}
