custom_build(
    ref='dispatcher-service',
    command='./gradlew bootBuildImage --imageName $EXPECTED_REF',
    deps=['build.gradle', 'src']
)

k8s_yaml(kustomize('k8s'))

k8s_resource('dispatcher-service', port_forwards=['9003'])
