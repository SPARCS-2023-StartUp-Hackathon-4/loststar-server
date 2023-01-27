package sparcs.loststar.util.s3

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile

@RestController
class AwsS3Controller(
    private val awsS3Service: AwsS3Service
) {
    @PostMapping("/upload")
    fun fileUpload(@RequestParam("image") multipartFile: MultipartFile): String {
        return awsS3Service.upload(multipartFile)
    }
}