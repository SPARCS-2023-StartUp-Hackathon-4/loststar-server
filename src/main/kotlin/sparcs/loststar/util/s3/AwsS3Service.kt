package sparcs.loststar.util.s3

import com.amazonaws.services.s3.AmazonS3Client
import com.amazonaws.services.s3.model.CannedAccessControlList
import com.amazonaws.services.s3.model.ObjectMetadata
import com.amazonaws.services.s3.model.PutObjectRequest
import com.amazonaws.util.IOUtils
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import sparcs.loststar.common.ImageResponse
import java.io.ByteArrayInputStream
import java.util.*

@Service
class AwsS3Service(
    private val amazonS3Client: AmazonS3Client,
    ) {

    @Value("\${cloud.aws.s3.bucket}")
    lateinit var bucket: String

    @Value("\${cloud.aws.s3.dir}")
    lateinit var dir: String

    fun upload(file: MultipartFile): ImageResponse {
        val fileName = UUID.randomUUID().toString() + "-" + file.originalFilename
        val objMeta = ObjectMetadata()

        val bytes = IOUtils.toByteArray(file.inputStream)
        objMeta.contentLength = bytes.size.toLong()

        val byteArrayIs = ByteArrayInputStream(bytes)

        amazonS3Client.putObject(
            PutObjectRequest(bucket, dir + fileName, byteArrayIs, objMeta)
            .withCannedAcl(CannedAccessControlList.PublicRead))

        return ImageResponse(amazonS3Client.getUrl(bucket, dir + fileName).toString())
    }
}